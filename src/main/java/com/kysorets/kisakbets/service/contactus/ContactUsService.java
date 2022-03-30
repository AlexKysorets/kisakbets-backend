package com.kysorets.kisakbets.service.contactus;

import com.kysorets.kisakbets.model.ContactUs;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactUsService {
    ContactUs getContactUsBySubject(String subject);
    ContactUs getContactUsByEmail(String email);
    List<ContactUs> getContactUs();
    void saveContactUs(ContactUs contactUs);
    void deleteContactUsByEmail(String email);
}
