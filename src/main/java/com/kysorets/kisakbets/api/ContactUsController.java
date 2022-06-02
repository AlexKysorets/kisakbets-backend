package com.kysorets.kisakbets.api;

import com.kysorets.kisakbets.model.ContactUs;
import com.kysorets.kisakbets.service.contactus.ContactUsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    // CREATE
    @PostMapping("/contact-us")
    public void createContactUs(@RequestBody ContactUs contactUs) {
        contactUsService.saveContactUs(contactUs);
    }

    // UPDATE
    @PutMapping("/contact-us")
    public void updateContactUs(@RequestBody ContactUs contactUs) {
        contactUsService.saveContactUs(contactUs);
    }

    // READ
    @GetMapping("/contact-us/{field}")
    public ResponseEntity<List<ContactUs>> getContactUsByEmailOrSubject(@PathVariable String field) {
        if (VALID_EMAIL_ADDRESS_REGEX.matcher(field).matches())
            return ResponseEntity.ok(contactUsService.getContactUsByEmail(field));
        else
            return ResponseEntity.ok(contactUsService.getContactUsBySubject(field));
    }

    // READ ALL
    @GetMapping("/contact-us-all")
    public ResponseEntity<List<ContactUs>> getAllContactUs(@RequestParam(required = false, defaultValue = "0") int page,
                                                           @RequestParam(required = false, defaultValue = "10") int size) {
        return ResponseEntity.ok(contactUsService.getContactUs(page, size));
    }

    // DELETE BY EMAIL
    @DeleteMapping("/contact-us/{email}")
    public void deleteContactUsByEmail(@PathVariable String email) {
        contactUsService.deleteContactUsByEmail(email);
    }
}
