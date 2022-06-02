package com.kysorets.kisakbets.service.role;

import com.kysorets.kisakbets.model.Role;
import com.kysorets.kisakbets.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImplementation implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.getByName(name);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteRoleByName(String name) {
        roleRepository.deleteByName(name);
    }
}
