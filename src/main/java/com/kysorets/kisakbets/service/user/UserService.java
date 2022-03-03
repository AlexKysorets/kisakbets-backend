package com.kysorets.kisakbets.service.user;

import com.kysorets.kisakbets.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User getUserByUsername(String username);
    List<User> getUsers();
    User saveUser(User user);
    void addRoleToUser(String username, String roleName);
}
