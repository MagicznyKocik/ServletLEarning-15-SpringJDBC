package pl.adamLupinski.dao;

import pl.adamLupinski.model.User;

public interface UserDao {

    public void create(User user);

    public User read(String pesel);

    public void update(User user);

    public void delete(User user);

}
