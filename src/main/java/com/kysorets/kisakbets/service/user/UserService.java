package com.kysorets.kisakbets.service.user;

import com.kysorets.kisakbets.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    List<User> getUsers(int page, int size);
    void saveUser(User user);
    void addRoleToUser(String username, String roleName);
    void deleteUserByUsername(String username);
    void deleteUserByEmail(String email);
}
