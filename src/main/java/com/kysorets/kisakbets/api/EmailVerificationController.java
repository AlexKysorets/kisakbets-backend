package com.kysorets.kisakbets.api;

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
import java.io.UnsupportedEncodingException;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class EmailVerificationController {
    private final HttpServletResponse response;
    private final JavaMailSender javaMailSender;

    @PostMapping("/email-verification")
    public void sendEmailVerificationLetter(@RequestBody UserEmailInfo userEmailInfo) {
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
    }
}

@Data
class UserEmailInfo {
    private String username;
    private String email;
}