package com.kysorets.kisakbets.service.contactus;

import com.kysorets.kisakbets.model.ContactUs;
import com.kysorets.kisakbets.repository.ContactUsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactUsServiceImplementation implements ContactUsService {
    private final ContactUsRepository contactUsRepository;

    @Override
    public ContactUs getContactUsBySubject(String subject) {
        return contactUsRepository.getBySubject(subject);
    }

    @Override
    public ContactUs getContactUsByEmail(String email) {
        return contactUsRepository.getByEmail(email);
    }

    @Override
    public List<ContactUs> getContactUs() {
        return contactUsRepository.findAll();
    }

    @Override
    public void saveContactUs(ContactUs contactUs) {
        contactUsRepository.save(contactUs);
    }

    @Override
    public void deleteContactUsByEmail(String email) {
        contactUsRepository.deleteByEmail(email);
    }
}
