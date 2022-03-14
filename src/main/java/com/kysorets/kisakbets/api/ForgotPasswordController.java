package com.kysorets.kisakbets.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kysorets.kisakbets.model.PasswordCode;
import com.kysorets.kisakbets.model.User;
import com.kysorets.kisakbets.security.EmailSender;
import com.kysorets.kisakbets.service.passwordcode.PasswordCodeService;
import com.kysorets.kisakbets.service.user.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    private final PasswordCodeService passwordCodeService;
    private final PasswordEncoder passwordEncoder;
    private final EmailSender emailSender;

    @PostMapping("/send")
    public void forgotPassword(@RequestBody ForgotPassInfo info) throws IOException {
        User user = userService.getUserByEmail(info.getEmail());
        if (user != null) {
            if (user.isVerified()) {
                String random = RandomString.make(50);
                LocalDateTime date = LocalDateTime.now().plusHours(24);
                PasswordCode passwordCode = new PasswordCode(random, date, user);
                user.setPasswordCode(random);

                // delete previous code
                PasswordCode previous = passwordCodeService.getPasswordCodeByUser(user);
                if (previous != null) {
                    passwordCodeService.deletePasswordCodeByCode(previous.getCode());
                }

                passwordCodeService.savePasswordCode(passwordCode);
                userService.saveUser(user);

                emailSender.sendEmail(info.getEmail(), "KisakBets forgot password", "To reset your password "+
                                "click this link ---> " + "http://localhost:8080/forgot-pass/verify?code=" + passwordCode.getCode(),
                        response);
            } else {
                Map<String, String> errors = new HashMap<>();
                errors.put("error", "User doesn't verify his email!");
                response.setContentType(APPLICATION_JSON_VALUE);
                response.setStatus(401);
                new ObjectMapper().writeValue(response.getOutputStream(), errors);
            }
        } else {
            Map<String, String> errors = new HashMap<>();
            errors.put("error", "User with such email doesn't exist!");
            response.setContentType(APPLICATION_JSON_VALUE);
            response.setStatus(401);
            new ObjectMapper().writeValue(response.getOutputStream(), errors);
        }
    }

    @GetMapping("/verify")
    public void checkPasswordCode(@RequestParam("code") String code) throws IOException {
        PasswordCode passwordCode = passwordCodeService.getPasswordCodeByCode(code);
        LocalDateTime date = LocalDateTime.now().minusHours(24);
        if (passwordCode.getExpiresAt().isAfter(date)) {
            // redirect to frontend with requesting password verification code
            // on frontend page user can create new password
            Map<String, String> result = new HashMap<>();
            result.put("message", "Redirect in future!");
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), result);
        } else {
            String random = RandomString.make(50);
            LocalDateTime dateTime = LocalDateTime.now().plusHours(24);
            User user = passwordCode.getUser();
            PasswordCode newPasswordCode = new PasswordCode(random, dateTime, user);
            user.setPasswordCode(random);

            // delete previous code
            PasswordCode previous = passwordCodeService.getPasswordCodeByUser(user);
            if (previous != null) {
                passwordCodeService.deletePasswordCodeByCode(previous.getCode());
            }

            passwordCodeService.savePasswordCode(newPasswordCode);
            userService.saveUser(user);

            emailSender.sendEmail(user.getEmail(), "KisakBets forgot password", "To reset your password " +
                    "click this link ---> " + "http://localhost:8080/forgot-pass/verify?code=" + newPasswordCode.getCode(),
                    response);

            // redirect to frontend with message that email password changing letter was sent again
        }
    }

    @PostMapping("/change")
    public void changeUserPassword(@RequestBody ChangePasswordInfo info) throws IOException {
        PasswordCode passwordCode = passwordCodeService.getPasswordCodeByCode(info.getPasswordCode());
        User user = userService.getUserByEmail(passwordCode.getUser().getEmail());
        user.setPassword(passwordEncoder.encode(info.getNewPassword()));
        user.setPasswordCode("");
        passwordCodeService.deletePasswordCodeByCode(info.getPasswordCode());
        userService.saveUser(user);
        Map<String, String> result = new HashMap<>();
        result.put("message", "Password was successfully changed!");
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), result);
    }
}

@Data
class ForgotPassInfo {
    private String email;
}

@Data
class ChangePasswordInfo {
    private String newPassword;
    private String passwordCode;
}