package com.kysorets.kisakbets.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kysorets.kisakbets.model.ContactUs;
import com.kysorets.kisakbets.model.User;
import com.kysorets.kisakbets.security.EmailSender;
import com.kysorets.kisakbets.service.contactus.ContactUsService;
import com.kysorets.kisakbets.service.user.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contact-us-message")
public class ContactUsMessageController {
    private final ContactUsService contactUsService;
    private final UserService userService;
    private final HttpServletResponse response;
    private final EmailSender emailSender;

    @PostMapping("/save")
    public void sendMessage(@RequestBody ContactUsInfo info) throws IOException {
        User user = userService.getUserByEmail(info.getEmail());
        if (user != null) {
            ContactUs contactUs = new ContactUs(info.getName(), info.getEmail(), info.getSubject(), info.getMessage(),
                    true, false);
            contactUsService.saveContactUs(contactUs);
        } else {
            ContactUs contactUs = new ContactUs(info.getName(), info.getEmail(), info.getSubject(), info.getMessage(),
                    false, false);
            contactUsService.saveContactUs(contactUs);
        }

        response.setContentType(APPLICATION_JSON_VALUE);
        Map<String, String> message = new HashMap<>();
        message.put("message", "successful sending message!");
        new ObjectMapper().writeValue(response.getOutputStream(), message);
    }

    @PostMapping("/send")
    public void sendEmailAnswer(@RequestBody ContactUsFeedbackInfo info) throws IOException {
        emailSender.sendEmail(info.getContactUs().getEmail(), "KisakBets answer", info.getContent(), response);
        info.getContactUs().setAnswered(true);
        contactUsService.saveContactUs(info.getContactUs());
    }
}

@Data
class ContactUsInfo {
    private String name;
    private String email;
    private String subject;
    private String message;
}

@Data
class ContactUsFeedbackInfo {
    private ContactUs contactUs;
    private String content;
}