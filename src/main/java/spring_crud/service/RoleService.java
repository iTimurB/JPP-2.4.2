package spring_crud.service;

import spring_crud.model.Role;

import java.util.List;

public interface RoleService {
    Role getRoleByName (String name);
    List<Role> getListRole ();
    Role getROleById (int id);
}
