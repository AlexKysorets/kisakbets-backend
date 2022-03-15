package com.kysorets.kisakbets.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kysorets.kisakbets.model.User;
import com.kysorets.kisakbets.service.user.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/settings")
public class AccountSettingsController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final HttpServletResponse response;

    @PostMapping("/password")
    public void changePassword(@RequestBody ChangePassword info) throws IOException {
        User user = userService.getUserByUsername(info.getUsername());
        if (passwordEncoder.matches(info.getCurrentPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(info.getNewPassword()));
            userService.saveUser(user);

            Map<String, String> result = new HashMap<>();
            result.put("message", "Password was changed successfully, please log in again!");
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), result);
        } else {
            Map<String, String> errors = new HashMap<>();
            errors.put("error", "Incorrect current password!");
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), errors);
        }
    }
}

@Data
class ChangePassword {
    private String username;
    private String currentPassword;
    private String newPassword;
}