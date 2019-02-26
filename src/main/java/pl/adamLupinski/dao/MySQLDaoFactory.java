package pl.adamLupinski.dao;

public class MySQLDaoFactory extends DaoFactory {
    @Override
    public BookDao getBookDao() {
        return new MySQLBookDao();
    }

    @Override
    public UserDao getUserDao() {
        return new MySQLUserDao();
    }
}
