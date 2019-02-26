package pl.adamLupinski.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import pl.adamLupinski.model.User;
import pl.adamLupinski.util.ConnectionProvider;
import pl.adamLupinski.util.DbOperationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySQLUserDao implements UserDao {


    private final static String CREATE =
            "INSERT INTO user(pesel, firstname, lastname) VALUES(:pesel, :firstName, :lastName);";
    private final static String READ =
            "SELECT pesel, firstname, lastname FROM user WHERE pesel = :pesel;";
    private final static String UPDATE =
            "UPDATE user SET firstname=:firstName, lastname=:lastName WHERE pesel = :pesel;";
    private final static String DELETE =
            "DELETE FROM user WHERE pesel=:pesel;";

    private NamedParameterJdbcTemplate template;

    public MySQLUserDao() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDSInstance());
    }

    @Override
    public void create(User user) {
        BeanPropertySqlParameterSource beanParamSource = new BeanPropertySqlParameterSource(user);
        template.update(CREATE, beanParamSource);
    }

    @Override
    public User read(String pesel) {
        User resultUser = null;
        SqlParameterSource namedParameters = new MapSqlParameterSource("pesel", pesel);
        List<User> userList = template.query(READ, namedParameters, BeanPropertyRowMapper.newInstance(User.class));
        if (userList.get(0) != null) {
            resultUser = userList.get(0);
        }
        return resultUser;
    }

    @Override
    public void update(User user) {
        BeanPropertySqlParameterSource beanParamSource = new BeanPropertySqlParameterSource(user);
        template.update(UPDATE, beanParamSource);
    }

    @Override
    public void delete(User user) {
        SqlParameterSource namedParameter = new MapSqlParameterSource("pesel", user.getPesel());
        template.update(DELETE, namedParameter);
    }
}
