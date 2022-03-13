package com.kysorets.kisakbets.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kysorets.kisakbets.model.PasswordCode;
import com.kysorets.kisakbets.model.User;
import com.kysorets.kisakbets.service.user.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forgot-pass")
public class ForgotPasswordController {
    private final UserService userService;
    private final HttpServletResponse response;

    @PostMapping("/send")
    public void forgotPassword(@RequestBody ForgotPassInfo info) throws IOException {
        User user = userService.getUserByEmail(info.getEmail());
        if (user != null) {
            String random = RandomString.make(50);
            LocalDateTime date = LocalDateTime.now().plusHours(24);
            PasswordCode passwordCode = new PasswordCode(random, date, user);
        } else {
            Map<String, String> errors = new HashMap<>();
            errors.put("error", "User with such email doesn't exist!");
            response.setContentType(APPLICATION_JSON_VALUE);
            response.setStatus(401);
            new ObjectMapper().writeValue(response.getOutputStream(), errors);
        }
    }
}

@Data
class ForgotPassInfo {
    private String email;
}