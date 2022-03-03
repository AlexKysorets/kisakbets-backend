package com.kysorets.kisakbets.service.user;

import com.kysorets.kisakbets.model.Role;
import com.kysorets.kisakbets.model.User;
import com.kysorets.kisakbets.repository.RoleRepository;
import com.kysorets.kisakbets.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {

    }
}
