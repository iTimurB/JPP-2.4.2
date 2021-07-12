package spring_crud.dao;

import spring_crud.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void save(User user);

    User showUser(int id);

    void update(int id, User user);

    void delete(int id);

    User getUserByName(String s);
}