package pl.adamLupinski.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import pl.adamLupinski.model.Book;
import pl.adamLupinski.util.ConnectionProvider;
import pl.adamLupinski.util.DbOperationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySQLBookDao implements BookDao {

    private final static String CREATE = "INSERT INTO book(isbn, title, description) VALUES(:isbn, :title, :description);";
    private final static String READ = "SELECT isbn, title, description FROM book WHERE isbn = :isbn;";
    private final static String UPDATE = "UPDATE book SET title=:title, description=:description WHERE isbn = c;";
    private final static String DELETE = "DELETE FROM book WHERE isbn=:isbn;";


//    implementing jdbc parameter template

    private NamedParameterJdbcTemplate template;

    public MySQLBookDao(){
        template =new NamedParameterJdbcTemplate(ConnectionProvider.getDSInstance());
    }

//    implementation of CRUD methods

   /* example without spring data was like :

    public  void create(Book book){
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setString(1, book.getIsbn());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbOperationException(e);
        }
    }
    */

//   and with spring data:

    public void create(Book book) {
        BeanPropertySqlParameterSource beanParamSource = new BeanPropertySqlParameterSource(book);
        template.update(CREATE, beanParamSource);
    }



    public Book read(String isbn) {
        Book resultBook = null;
        SqlParameterSource namedParameters = new MapSqlParameterSource("isbn", isbn);
        List<Book> bookList = template
                .query(READ, namedParameters, BeanPropertyRowMapper.newInstance(Book.class));
        if (bookList.get(0) != null) {
            resultBook = bookList.get(0);
        }
        return resultBook;
    }

    public void update(Book book) {
        BeanPropertySqlParameterSource beanParamSource = new BeanPropertySqlParameterSource(book);
        template.update(UPDATE, beanParamSource);
    }


    public void delete(Book book) {
        SqlParameterSource namedParameter = new MapSqlParameterSource("isbn", book.getIsbn());
        template.update(DELETE, namedParameter);
    }




}
