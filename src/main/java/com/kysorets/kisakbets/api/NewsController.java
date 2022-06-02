package com.kysorets.kisakbets.api;

import com.kysorets.kisakbets.model.News;
import com.kysorets.kisakbets.service.news.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NewsController {
    private final NewsService newsService;

    // CREATE
    @PostMapping("/news")
    public void createNews(@RequestBody News news) {
        newsService.saveNews(news);
    }

    // UPDATE
    @PutMapping("/news")
    public void updateNews(@RequestBody News news) {
        newsService.saveNews(news);
    }

    // READ
    @GetMapping("/news/{name}")
    public ResponseEntity<News> getNewsByName(@PathVariable String name) {
        return ResponseEntity.ok(newsService.getNewsByName(name));
    }

    // READ ALL
    @GetMapping("/news-all")
    public ResponseEntity<List<News>> getAllNews(@RequestParam(required = false, defaultValue = "0") int page,
                                                 @RequestParam(required = false, defaultValue = "10") int size) {
        return ResponseEntity.ok(newsService.getNews(page, size));
    }

    // DELETE
    @DeleteMapping("/news/{name}")
    public void deleteNewsByName(@PathVariable String name) {
        newsService.deleteNewsByName(name);
    }
}
