package com.kysorets.kisakbets.api;

import com.kysorets.kisakbets.model.User;
import com.kysorets.kisakbets.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getUsers();
    }
}
