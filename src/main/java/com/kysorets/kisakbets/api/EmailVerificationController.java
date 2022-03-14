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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailVerificationController {
    private final HttpServletResponse response;
    private final UserService userService;
    private final VerificationCodeService verificationCodeService;
    private final EmailSender emailSender;

    @PostMapping("/send")
    public void sendEmailVerificationLetter(@RequestBody UserEmailInfo userEmailInfo) throws IOException {
        String random = RandomString.make(50);
        LocalDateTime date = LocalDateTime.now().plusHours(24);
        User user = userService.getUserByUsername(userEmailInfo.getUsername());
        VerificationCode verificationCode = new VerificationCode(random, date, user);
        user.setCode(random);

        // delete previous code
        VerificationCode previous = verificationCodeService.getVerificationCodeByUser(user);
        if (previous != null) {
            verificationCodeService.deleteVerificationCodeByCode(previous.getCode());
        }

        verificationCodeService.saveVerificationCode(verificationCode);
        userService.saveUser(user);

        emailSender.sendEmail(userEmailInfo.getEmail(), "KisakBets email verification", "To verify your " +
                "email click this link ---> " + "http://localhost:8080/email/verify?code=" + verificationCode.getCode(),
                response);
    }

    @GetMapping("/verify")
    public void checkVerificationCode(@RequestParam("code") String code) throws IOException {
        VerificationCode verificationCode = verificationCodeService.getVerificationCodeByCode(code);
        LocalDateTime date = LocalDateTime.now().minusHours(24);
        if (verificationCode.getExpiresAt().isAfter(date)) {
            User user = verificationCode.getUser();
            user.setVerified(true);
            user.setCode("");
            userService.saveUser(user);
            verificationCodeService.deleteVerificationCodeByCode(code);
            Map<String, String> result = new HashMap<>();
            result.put("message", "Successful verifying email!");
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), result);
        } else {
            // here
            String random = RandomString.make(50);
            LocalDateTime dateTime = LocalDateTime.now().plusHours(24);
            User user = verificationCode.getUser();
            VerificationCode newVerificationCode = new VerificationCode(random, dateTime, user);
            user.setCode(random);

            // delete previous code
            VerificationCode previous = verificationCodeService.getVerificationCodeByUser(user);
            if (previous != null) {
                verificationCodeService.deleteVerificationCodeByCode(previous.getCode());
            }

            verificationCodeService.saveVerificationCode(newVerificationCode);
            userService.saveUser(user);

//            String toAddress = user.getEmail();
//            String fromAddress = "alexproba140920@gmail.com";
//            String senderName = "Kisak Inc";
//            String subject = "KisakBets email verification";
//            String content = "To verify your email click this link ---> " + "http://localhost:8080/email/verify?code=" +
//                    newVerificationCode.getCode();
//
//            MimeMessage message = javaMailSender.createMimeMessage();
//            MimeMessageHelper messageHelper = new MimeMessageHelper(message);
//
//            try {
//                messageHelper.setFrom(fromAddress, senderName);
//                messageHelper.setTo(toAddress);
//                messageHelper.setSubject(subject);
//                messageHelper.setText(content, true);
//            } catch (MessagingException e) {
//                e.printStackTrace();
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//
//            try {
//                javaMailSender.send(message);
//                response.setContentType(APPLICATION_JSON_VALUE);
//                Map<String, String> result = new HashMap<>();
//                result.put("message", "Email letter was sent successful!");
//                new ObjectMapper().writeValue(response.getOutputStream(), result);
//            } catch (MailSendException e) {
//                e.printStackTrace();
//                response.setContentType(APPLICATION_JSON_VALUE);
//                response.setStatus(502);
//                Map<String, String> errors = new HashMap<>();
//                errors.put("error", "Failed to send email message!");
//                new ObjectMapper().writeValue(response.getOutputStream(), errors);
//            }

            // redirect to frontend with message that email verification letter was sent again
        }
    }
}

@Data
class UserEmailInfo {
    private String username;
    private String email;
}