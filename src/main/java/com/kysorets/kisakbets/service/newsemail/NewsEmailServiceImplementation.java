package com.kysorets.kisakbets.service.newsemail;

import com.kysorets.kisakbets.model.NewsEmail;
import com.kysorets.kisakbets.repository.NewsEmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsEmailServiceImplementation implements NewsEmailService{
    private final NewsEmailRepository newsEmailRepository;

    @Override
    public NewsEmail getNewsEmailByEmail(String email) {
        return newsEmailRepository.getByEmail(email);
    }

    @Override
    public List<NewsEmail> getNewsEmails() {
        return newsEmailRepository.findAll();
    }

    @Override
    public void saveNewsEmail(NewsEmail newsEmail) {
        newsEmailRepository.save(newsEmail);
    }

    @Override
    public void deleteNewsEmailByEmail(String email) {
        newsEmailRepository.deleteByEmail(email);
    }
}
