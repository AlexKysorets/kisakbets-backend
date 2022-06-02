package com.kysorets.kisakbets.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kysorets.kisakbets.model.News;
import com.kysorets.kisakbets.model.NewsEmail;
import com.kysorets.kisakbets.security.EmailSender;
import com.kysorets.kisakbets.service.news.NewsService;
import com.kysorets.kisakbets.service.newsemail.NewsEmailService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsletterController {
    private final NewsService newsService;
    private final HttpServletResponse response;
    private final EmailSender emailSender;
    private final NewsEmailService newsEmailService;

    @PostMapping("/create")
    public void createNews(@RequestBody CreateNewsInfo info) throws IOException {
        News news = new News(info.getName(), info.getContent(), LocalDateTime.now());
        newsService.saveNews(news);

        Map<String, String> result = new HashMap<>();
        result.put("message", "news were created successfully!");
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), result);
    }

    @PostMapping("/send")
    public void sendNewsLetter(@RequestBody News news) throws IOException {
        List<NewsEmail> emails = newsEmailService.getNewsEmailsForOnce();
        for (NewsEmail email:
             emails) {
            emailSender.sendEmail(email.getEmail(), "KisakBets " + news.getName(), news.getContent(), response);
        }
    }

    @PostMapping("/subscribe")
    public void subscribeToNewsLetter(@RequestBody SubscribeInfo info) throws IOException {
        NewsEmail check = newsEmailService.getNewsEmailByEmail(info.getEmail());
        if (check == null) {
            NewsEmail newsEmail = new NewsEmail(info.getEmail());
            newsEmailService.saveNewsEmail(newsEmail);

            Map<String, String> result = new HashMap<>();
            result.put("message", "successful subscribing!");
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), result);
        } else {
            Map<String, String> errors = new HashMap<>();
            errors.put("error", "you already subscribed to newsletter!");
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), errors);
        }
    }
}

@Data
class CreateNewsInfo {
    private String name;
    private String content;
}

@Data
class SubscribeInfo {
    private String email;
}