package com.kysorets.kisakbets.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kysorets.kisakbets.model.Role;
import com.kysorets.kisakbets.model.User;
import com.kysorets.kisakbets.security.token.Token;
import com.kysorets.kisakbets.service.role.RoleService;
import com.kysorets.kisakbets.service.user.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class SignUpController {
    private final UserService userService;
    private final RoleService roleService;
    private final HttpServletResponse response;
    private final HttpServletRequest request;

    @PostMapping("/signup")
    public void tryToSignUp(@RequestBody UserInfo userInfo) throws IOException {
        User user = userService.getUserByUsername(userInfo.getUsername());
        if (user == null) {
            User user1 = userService.getUserByEmail(userInfo.getEmail());
            if (user1 == null) {
                successfulRegistration(request, response, userInfo);
            } else {
                unsuccessfulRegistration(response, "Email");
            }
        } else {
            unsuccessfulRegistration(response, "Username");
        }
    }

    public void successfulRegistration(HttpServletRequest request, HttpServletResponse response, UserInfo userInfo) throws IOException {
        Role role = roleService.getRoleByName("ROLE_USER");
        User user = new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.getEmail(), false,
                new ArrayList<>(List.of(role)), "");
        userService.saveUser(user);

        String access_token = new Token().giveAccessToken(user.getUsername(), request, user.getRoles(), null);
        String refresh_token = new Token().giveRefreshToken(user.getUsername(), request);

        Map<String, Object> result = new HashMap<>();
        result.put("access_token", access_token);
        result.put("refresh_token", refresh_token);
        result.put("username", user.getUsername());
        result.put("password", user.getPassword());
        result.put("email", user.getEmail());
        result.put("isVerified", user.isVerified());

        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), result);
    }

    public void unsuccessfulRegistration(HttpServletResponse response, String type) throws IOException {
        Map<String, String> errors = new HashMap<>();
        if (type.equals("Email")) {
            errors.put("error", "User with such email already exists!");
        } else {
            errors.put("error", "User with such username already exists!");
        }
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(401);
        new ObjectMapper().writeValue(response.getOutputStream(), errors);
    }
}

@Data
class UserInfo {
    private String username;
    private String email;
    private String password;
}
