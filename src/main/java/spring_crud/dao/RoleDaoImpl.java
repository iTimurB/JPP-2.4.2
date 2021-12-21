package spring_crud.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import spring_crud.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Role getRoleByName(String role) {
        return (Role) entityManager.createQuery("SELECT r FROM Role r where r.role=:role")
                .setParameter("role", role)
                .getSingleResult();
    }

    @Override
    @Transactional
    public Role findRoleBiId(long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    @Transactional
    public List<Role> getListRole() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }
}
