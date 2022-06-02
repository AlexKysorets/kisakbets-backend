package com.kysorets.kisakbets.api;

import com.kysorets.kisakbets.model.NewsEmail;
import com.kysorets.kisakbets.service.newsemail.NewsEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NewsEmailController {
    private final NewsEmailService newsEmailService;

    // CREATE
    @PostMapping("/news-email")
    public void createNewsEmail(@RequestBody NewsEmail newsEmail) {
        newsEmailService.saveNewsEmail(newsEmail);
    }

    // UPDATE
    @PutMapping("/news-email")
    public void updateNewsEmail(@RequestBody NewsEmail newsEmail) {
        newsEmailService.saveNewsEmail(newsEmail);
    }

    // READ
    @GetMapping("/news-email/{email}")
    public ResponseEntity<NewsEmail> getNewsEmailByEmail(@PathVariable String email) {
        return ResponseEntity.ok(newsEmailService.getNewsEmailByEmail(email));
    }

    // READ ALL
    @GetMapping("/news-emails")
    public ResponseEntity<List<NewsEmail>> getAllNewsEmails(@RequestParam(required = false, defaultValue = "0") int page,
                                                            @RequestParam(required = false, defaultValue = "10") int size) {
        return ResponseEntity.ok(newsEmailService.getNewsEmails(page, size));
    }

    // DELETE
    @DeleteMapping("/news-email/{email}")
    public void deleteNewsEmailsByEmail(@PathVariable String email) {
        newsEmailService.deleteNewsEmailByEmail(email);
    }
}
