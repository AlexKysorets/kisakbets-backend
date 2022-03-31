package com.kysorets.kisakbets.api;

import com.kysorets.kisakbets.model.News;
import com.kysorets.kisakbets.service.news.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NewsController {
    private final NewsService newsService;

    // CREATE OR UPDATE
    @PostMapping("/news")
    public void createOrUpdateNews(@RequestBody News news) {
        newsService.saveNews(news);
    }

    // READ
    @GetMapping("/news/{name}")
    public News getNewsByName(@PathVariable String name) {
        return newsService.getNewsByName(name);
    }

    // READ ALL
    @GetMapping("/news-all")
    public List<News> getAllNews() {
        return newsService.getNews();
    }

    // DELETE
    @DeleteMapping("/news/{name}")
    public void deleteNewsByName(@PathVariable String name) {
        newsService.deleteNewsByName(name);
    }
}
