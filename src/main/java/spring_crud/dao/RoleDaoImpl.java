package spring_crud.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import spring_crud.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        List<Role> roleList = entityManager.createQuery("SELECT r FROM Role r where r.role=:role")
                .setParameter("role", role)
                .getResultList();
        if (roleList.isEmpty()){
            return null;
        }
        return roleList.get(0);
    }

    @Override
    @Transactional
    public Role findRoleBiId(int id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    @Transactional
    public List<Role> getListRole() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }

    @Override
    @Transactional
    public Set<Role> getRoleSetById(int [] id) {
        Set<Role> roleIdSet = new HashSet<>();
        for (int role :id){
            roleIdSet.add(findRoleBiId(role));
        }
        return roleIdSet;
    }
}
