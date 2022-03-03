package com.kysorets.kisakbets.service.role;

import com.kysorets.kisakbets.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImplementation implements RoleService {
    private final RoleService roleService;

    @Override
    public Role getRoleByName(String name) {
        return roleService.getRoleByName(name);
    }

    @Override
    public Role saveRole(Role role) {
        return roleService.saveRole(role);
    }

    @Override
    public List<Role> getRoles() {
        return roleService.getRoles();
    }

    @Override
    public void deleteRoleByName(String name) {
        roleService.deleteRoleByName(name);
    }
}
