package com.kysorets.kisakbets.service.role;

import com.kysorets.kisakbets.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    Role getRoleByName(String name);
    Role saveRole(Role role);
    List<Role> getRoles();
}
