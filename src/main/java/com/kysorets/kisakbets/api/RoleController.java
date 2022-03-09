package com.kysorets.kisakbets.api;

import com.kysorets.kisakbets.model.Role;
import com.kysorets.kisakbets.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RoleController {
    private final RoleService roleService;

    // CREATE AND UPDATE
    @PostMapping("/role")
    public void createOrUpdateRole(@RequestBody Role role) {
        roleService.saveRole(role);
    }

    // READ
    @GetMapping("/role/{name}")
    public Role getRoleByName(@PathVariable String name) {
        return roleService.getRoleByName(name);
    }

    // READ ALL
    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getRoles();
    }

    // DELETE
    @DeleteMapping("/role/{name}")
    public void deleteRoleByName(@PathVariable String name) {
        roleService.deleteRoleByName(name);
    }
}
