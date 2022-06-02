package com.kysorets.kisakbets.service.contactus;

import com.kysorets.kisakbets.model.ContactUs;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactUsService {
    List<ContactUs> getContactUsBySubject(String subject);
    List<ContactUs> getContactUsByEmail(String email);
    List<ContactUs> getContactUs(int page, int size);
    void saveContactUs(ContactUs contactUs);
    void deleteContactUsByEmail(String email);
}
