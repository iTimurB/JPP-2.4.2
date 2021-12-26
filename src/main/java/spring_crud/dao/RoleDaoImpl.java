package spring_crud.dao;

import org.springframework.stereotype.Component;
import spring_crud.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

@Component
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<Role> getRolesByIds(List<Long> ids) {
        TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM Role r WHERE r.id IN :ids", Role.class).setParameter("ids", ids);
        return Set.copyOf(query.getResultList());
    }
}
