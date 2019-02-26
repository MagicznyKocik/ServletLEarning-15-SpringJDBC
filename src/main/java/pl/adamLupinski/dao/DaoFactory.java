package pl.adamLupinski.dao;

public abstract class DaoFactory {

    public static final int MYSQL_DAO=1;

    public abstract BookDao getBookDao();

    public abstract UserDao getUserDao();

    private static DaoFactory instance;

    public static DaoFactory getDaoFactory(int factoryType) {
        if (instance == null){
            if (factoryType == MYSQL_DAO) {
                instance = new MySQLDaoFactory();
            }
        }
        return instance;
    }

}
