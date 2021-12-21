package spring_crud.dao;

import spring_crud.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void save(User user);

    User showUserById(int id);

    void update(long id, User user);

    void delete(int id);

    User getUserByName(String name);

    String bcryptPass(String pass);
}