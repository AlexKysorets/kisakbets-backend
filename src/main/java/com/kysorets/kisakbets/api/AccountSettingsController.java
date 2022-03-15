package com.kysorets.kisakbets.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kysorets.kisakbets.model.User;
import com.kysorets.kisakbets.model.VerificationCode;
import com.kysorets.kisakbets.security.EmailSender;
import com.kysorets.kisakbets.service.user.UserService;
import com.kysorets.kisakbets.service.verificationcode.VerificationCodeService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
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
    private final EmailSender emailSender;
    private final VerificationCodeService verificationCodeService;

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

    @PostMapping("/email")
    public void sendLetterToNewEmail(@RequestBody ChangeEmail info) throws IOException {
        User user = userService.getUserByUsername(info.getUsername());
        if (passwordEncoder.matches(info.getPassword(), user.getPassword())) {
            String random = RandomString.make(50);
            LocalDateTime date = LocalDateTime.now().plusHours(24);
            VerificationCode code = new VerificationCode(random, date, user);

            // delete previous code
            VerificationCode previous = verificationCodeService.getVerificationCodeByUser(user);
            if (previous != null) {
                verificationCodeService.deleteVerificationCodeByCode(previous.getCode());
            }

            user.setCode(random);
            userService.saveUser(user);
            verificationCodeService.saveVerificationCode(code);

            emailSender.sendEmail(info.getNewEmail(), "KisakBets email changing", "To change your email " +
                    "click this link ---> " + "http://localhost:8080/settings/email/change?code=" + code.getCode() +
                    "&email=" + info.getNewEmail(), response);
        } else {
            Map<String, String> errors = new HashMap<>();
            errors.put("error", "Incorrect password!");
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), errors);
        }
    }

    @GetMapping("/email/change")
    public void changeEmail(@RequestParam("code") String code, @RequestParam("email") String newEmail) throws IOException {
        VerificationCode verificationCode = verificationCodeService.getVerificationCodeByCode(code);
        LocalDateTime date = LocalDateTime.now().minusHours(24);
        if (verificationCode.getExpiresAt().isAfter(date)) {
            User user = verificationCode.getUser();
            user.setCode("");
            user.setEmail(newEmail);
            userService.saveUser(user);
            verificationCodeService.deleteVerificationCodeByCode(code);
            // redirect to frontend
            Map<String, String> result = new HashMap<>();
            result.put("message", "Successful email changing!");
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), result);
        }
    }

    @PostMapping("/username")
    public void changeUsername(@RequestBody ChangeUsername info) throws IOException {
        User user = userService.getUserByUsername(info.getCurrentUsername());
        if (passwordEncoder.matches(info.getPassword(), user.getPassword())) {
            user.setUsername(info.getNewUsername());
            userService.saveUser(user);

            Map<String, String> result = new HashMap<>();
            result.put("message", "Username was changed successfully, please log in again!");
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), result);
        } else {
            Map<String, String> errors = new HashMap<>();
            errors.put("error", "Incorrect password!");
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

@Data
class ChangeEmail {
    private String username;
    private String password;
    private String newEmail;
}

@Data
class ChangeUsername {
    private String currentUsername;
    private String password;
    private String newUsername;
}