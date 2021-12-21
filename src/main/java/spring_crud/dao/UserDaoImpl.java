package spring_crud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import spring_crud.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void save(User user) {
        String passBCrypt = bcryptPass(user.getPassword());
        user.setPassword(passBCrypt);
        entityManager.persist(user);
    }

    @Override
    public User showUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(long id, User updatedUser) {
        String passBCrypt = bcryptPass(updatedUser.getPassword());
        updatedUser.setPassword(passBCrypt);
        entityManager.merge(updatedUser);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(showUserById(id));
    }

    @Override
    public User getUserByName(String email) {
        return entityManager.createQuery("SELECT u FROM User u join fetch u.roles WHERE u.email=:email", User.class)//с ролью
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public String bcryptPass(String pass) {
        return bCryptPasswordEncoder.encode(pass);
    }
}
