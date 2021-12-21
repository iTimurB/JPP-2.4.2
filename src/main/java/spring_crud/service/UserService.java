package spring_crud.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import spring_crud.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void save(User user);

    User showUserById(int id);

    void update(long id, User user);

    void delete(int id);

    User getUserByName(String name);
}
