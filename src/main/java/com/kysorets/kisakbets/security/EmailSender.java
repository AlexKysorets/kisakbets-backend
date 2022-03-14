package com.kysorets.kisakbets.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
public class EmailSender {
    private final JavaMailSender javaMailSender;

    public void sendEmail(String toAddress, String subject, String content, HttpServletResponse response)
            throws IOException {
        String fromAddress = "alexproba140920@gmail.com";
        String senderName = "Kisak Inc";

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

        try {
            javaMailSender.send(message);
            response.setContentType(APPLICATION_JSON_VALUE);
            Map<String, String> result = new HashMap<>();
            result.put("message", "Email letter was sent successful!");
            new ObjectMapper().writeValue(response.getOutputStream(), result);
        } catch (MailSendException e) {
            e.printStackTrace();
            response.setContentType(APPLICATION_JSON_VALUE);
            response.setStatus(502);
            Map<String, String> errors = new HashMap<>();
            errors.put("error", "Failed to send email message!");
            new ObjectMapper().writeValue(response.getOutputStream(), errors);
        }
    }
}
