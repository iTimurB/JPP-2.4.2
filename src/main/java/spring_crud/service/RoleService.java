package spring_crud.service;

import spring_crud.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Role getRoleByName(String name);

    Role findRoleBiId(int id);

    List<Role> getListRole();

    Set<Role> getRoleSetById(int[] id);
}
