package spring_crud.dao;

import org.springframework.stereotype.Component;
import spring_crud.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getRoleByName(String role) {
        List<Role> roleList = entityManager.createQuery("SELECT r FROM Role r where r.role=:role")
                .setParameter("role", role)
                .getResultList();
        if (roleList.isEmpty()){
            return null;
        }
        return roleList.get(0);
    }

    @Override
    public List<Role> getListRole() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }

    @Override
    public Role getROleById(int id) {
        return entityManager.find(Role.class, id);
    }
}
