package com.kysorets.kisakbets.service.newsemail;

import com.kysorets.kisakbets.model.NewsEmail;
import com.kysorets.kisakbets.repository.NewsEmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<NewsEmail> getNewsEmails(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var newsEmails = newsEmailRepository.findAll(pageable);
        return newsEmails.toList();
    }

    @Override
    public List<NewsEmail> getNewsEmailsForOnce() {
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
