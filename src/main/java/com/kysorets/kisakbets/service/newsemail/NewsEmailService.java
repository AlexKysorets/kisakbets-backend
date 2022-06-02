package com.kysorets.kisakbets.service.newsemail;

import com.kysorets.kisakbets.model.NewsEmail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NewsEmailService {
    NewsEmail getNewsEmailByEmail(String email);
    List<NewsEmail> getNewsEmails(int page, int size);
    List<NewsEmail> getNewsEmailsForOnce();
    void saveNewsEmail(NewsEmail newsEmail);
    void deleteNewsEmailByEmail(String email);
}
