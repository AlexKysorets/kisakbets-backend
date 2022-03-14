package com.kysorets.kisakbets.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kysorets.kisakbets.model.User;
import com.kysorets.kisakbets.security.EmailSender;
import com.kysorets.kisakbets.service.user.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forgot-username")
public class ForgotUsernameController {
    private final UserService userService;
    private final HttpServletResponse response;
    private final EmailSender emailSender;

    @PostMapping("/send")
    public void forgotUsername(@RequestBody ForgotUsernameInfo info) throws IOException {
        User user = userService.getUserByEmail(info.getEmail());
        if (user != null) {
            if (user.isVerified()) {
                emailSender.sendEmail(info.getEmail(), "KisakBets forgot username", "Your username ---> " +
                        user.getUsername(), response);
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
}

@Data
class ForgotUsernameInfo {
    private String email;
}