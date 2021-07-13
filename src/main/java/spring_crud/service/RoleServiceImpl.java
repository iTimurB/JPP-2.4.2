package spring_crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring_crud.dao.RoleDao;
import spring_crud.model.Role;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional
    public Role getRoleByName(String role) {
        return roleDao.getRoleByName(role);
    }

    @Override
    @Transactional
    public Role findRoleBiId(int id) {
        return roleDao.findRoleBiId(id);
    }

    @Override
    @Transactional
    public List<Role> getListRole() {
        return roleDao.getListRole();
    }

    @Override
    public Set<Role> getRoleSetById(int[] id) {
        return null;
    }

}
