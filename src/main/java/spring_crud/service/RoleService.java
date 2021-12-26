package spring_crud.service;

import spring_crud.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    Set<Role> getRolesByIds (List <Long> ids);
}
