package spring_crud.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import spring_crud.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    public User showUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void update(int id, User updatedUser) {
        User userTemp = entityManager.find(User.class, id);
        userTemp.setName(updatedUser.getName());
        userTemp.setAge(updatedUser.getAge());
        userTemp.setEmail(updatedUser.getEmail());
        userTemp.setPassword(updatedUser.getPassword());
    }

    @Override
    @Transactional
    public void delete(int id) {
        entityManager.remove(showUser(id));
    }

    @Override
    public User getUserByName(String email) {
        List<User> list = entityManager.createQuery("SELECT u FROM User u WHERE u.email=:email")
                .setParameter("email", email)
                .getResultList();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
