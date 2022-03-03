package com.kysorets.kisakbets.api;

import com.kysorets.kisakbets.model.User;
import com.kysorets.kisakbets.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    // CREATE AND UPDATE
    @PostMapping("/user")
    public void createUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    // READ
    @GetMapping("/user/{field}")
    public User getUserByUsernameOrEmail(@PathVariable String field) {
        if (VALID_EMAIL_ADDRESS_REGEX.matcher(field).matches())
            return userService.getUserByEmail(field);
        else
            return userService.getUserByUsername(field);
    }

    // READ ALL
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    // DELETE
    @DeleteMapping("/user/{field}")
    public void deleteUserByUsernameOrEmail(@PathVariable String field) {
        if (VALID_EMAIL_ADDRESS_REGEX.matcher(field).matches())
            userService.deleteUserByEmail(field);
        else
            userService.deleteUserByUsername(field);
    }
}
