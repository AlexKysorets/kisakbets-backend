package com.kysorets.kisakbets.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kysorets.kisakbets.model.News;
import com.kysorets.kisakbets.service.news.NewsService;
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
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsletterController {
    private final NewsService newsService;
    private final HttpServletResponse response;

    @PostMapping("/create")
    public void createNews(@RequestBody CreateNewsInfo info) throws IOException {
        News news = new News(info.getName(), info.getContent(), LocalDateTime.now());
        newsService.saveNews(news);

        Map<String, String> result = new HashMap<>();
        result.put("message", "news were created successfully!");
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), result);
    }
}

@Data
class CreateNewsInfo {
    private String name;
    private String content;
}