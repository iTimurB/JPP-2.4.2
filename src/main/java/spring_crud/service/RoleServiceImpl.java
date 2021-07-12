package spring_crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring_crud.dao.RoleDao;
import spring_crud.model.Role;

import java.util.List;

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
    public List<Role> getListRole() {
        return roleDao.getListRole();
    }

    @Override
    @Transactional
    public Role getROleById(int id) {
        return roleDao.getROleById(id);
    }
}
