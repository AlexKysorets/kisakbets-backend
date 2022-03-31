package com.kysorets.kisakbets.api;

import com.kysorets.kisakbets.model.NewsEmail;
import com.kysorets.kisakbets.service.newsemail.NewsEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NewsEmailController {
    private final NewsEmailService newsEmailService;

    // CREATE OR UPDATE
    @PostMapping("/news-email")
    public void createOrUpdateNewsEmail(@RequestBody NewsEmail newsEmail) {
        newsEmailService.saveNewsEmail(newsEmail);
    }

    // READ
    @GetMapping("/news-email/{email}")
    public NewsEmail getNewsEmailByEmail(@PathVariable String email) {
        return newsEmailService.getNewsEmailByEmail(email);
    }

    // READ ALL
    @GetMapping("/news-emails")
    public List<NewsEmail> getAllNewsEmails() {
        return newsEmailService.getNewsEmails();
    }

    // DELETE
    @DeleteMapping("/news-email/{email}")
    public void deleteNewsEmailsByEmail(@PathVariable String email) {
        newsEmailService.deleteNewsEmailByEmail(email);
    }
}
