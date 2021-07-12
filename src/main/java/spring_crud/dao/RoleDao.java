package spring_crud.dao;

import spring_crud.model.Role;

import java.util.List;

public interface RoleDao {
    Role getRoleByName (String name);
    List<Role> getListRole ();
    Role getROleById (int id);
}
