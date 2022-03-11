package com.kysorets.kisakbets.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kysorets.kisakbets.model.User;
import com.kysorets.kisakbets.service.user.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
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
import java.util.Random;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailVerificationController {
    private final HttpServletResponse response;
    private final JavaMailSender javaMailSender;
    private final UserService userService;

    @PostMapping("/send")
    public void sendEmailVerificationLetter(@RequestBody UserEmailInfo userEmailInfo) throws IOException {
        String code = RandomString.make(6);
        String toAddress = userEmailInfo.getEmail();
        String fromAddress = "alexproba140920@gmail.com";
        String senderName = "Kisak Inc";
        String subject = "KisakBets email verification";
        String content = "Verification code ---> " + code;

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);

        try {
            messageHelper.setFrom(fromAddress, senderName);
            messageHelper.setTo(toAddress);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(message);
        Map<String, String> info = new HashMap<>();
        info.put("code", code);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), info);
    }

    @PostMapping("/verify")
    public void checkVerificationCode() {
    }
}

@Data
class UserEmailInfo {
    private String username;
    private String email;
}