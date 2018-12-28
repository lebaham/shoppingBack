package com.shopping.shopping.serviceImp;

import com.shopping.shopping.dao.RoleDao;
import com.shopping.shopping.model.Role;
import com.shopping.shopping.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImp extends AbstractShoppingServiceImp<Role, Long> implements RoleService {
}
