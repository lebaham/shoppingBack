package com.shopping.shopping.service;

import com.shopping.shopping.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role addRole(Role role);
    Role updateRole(Role Role);
    Optional<Role> getRole(Long idRole);
    void deleteRole(Long idRole);
    List<Role> getRoles();
}
