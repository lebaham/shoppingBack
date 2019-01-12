package com.shopping.shopping.serviceImp;

import com.shopping.shopping.dao.RoleDao;
import com.shopping.shopping.model.Role;
import com.shopping.shopping.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Override
    public Role addRole(Role role) {
        return roleDao.save(role);
    }

    @Override
    public Role updateRole(Role role) {
        return roleDao.save(role);
    }

    @Override
    public Optional<Role> getRole(Long idRole) {
        return roleDao.findById(idRole);
    }

    @Override
    public void deleteRole(Long idRole) {
        roleDao.deleteById(idRole);
    }

    @Override
    public List<Role> getRoles() {
        return roleDao.findAll();
    }
}
