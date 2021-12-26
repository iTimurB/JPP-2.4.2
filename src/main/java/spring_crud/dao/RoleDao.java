package spring_crud.dao;

import spring_crud.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    Set<Role> getRolesByIds (List <Long> ids);
}
