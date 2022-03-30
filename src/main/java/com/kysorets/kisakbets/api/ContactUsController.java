package com.kysorets.kisakbets.api;

import com.kysorets.kisakbets.model.ContactUs;
import com.kysorets.kisakbets.service.contactus.ContactUsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ContactUsController {
    private final ContactUsService contactUsService;
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    // CREATE AND UPDATE
    @PostMapping("/contact-us")
    public void createOrSaveContactUs(@RequestBody ContactUs contactUs) {
        contactUsService.saveContactUs(contactUs);
    }

    // READ
    @GetMapping("/contact-us/{field}")
    public ContactUs getContactUsByEmailOrSubject(@PathVariable String field) {
        if (VALID_EMAIL_ADDRESS_REGEX.matcher(field).matches())
            return contactUsService.getContactUsByEmail(field);
        else
            return contactUsService.getContactUsBySubject(field);
    }

    // READ ALL
    @GetMapping("/contact-us-all")
    public List<ContactUs> getAllContactUs() {
        return contactUsService.getContactUs();
    }

    // DELETE BY EMAIL
    @DeleteMapping("/contact-us/{email}")
    public void deleteContactUsByEmail(@PathVariable String email) {
        contactUsService.deleteContactUsByEmail(email);
    }
}
