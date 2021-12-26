package spring_crud.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring_crud.dao.UserDao;
import spring_crud.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public User showUserById(long id) {
        return userDao.showUserById(id);
    }

    @Override
    @Transactional
    public void update(long id, User user) {
        if (!user.getPassword().equals("")){
            user.setPassword(user.getPassword());
        }
        userDao.update(id, user);
    }

    @Override
    @Transactional
    public void delete(long id) {
        userDao.delete(id);
    }

    @Override
    public User getUserByNameWithRoles(String name) {
        return userDao.getUserByNameWithRoles(name);
    }
}
