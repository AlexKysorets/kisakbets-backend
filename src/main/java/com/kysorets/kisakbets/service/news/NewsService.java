package com.kysorets.kisakbets.service.news;

import com.kysorets.kisakbets.model.News;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface NewsService {
    News getNewsByName(String name);
    News getNewsByDate(LocalDateTime date);
    List<News> getNews(int page, int size);
    void saveNews(News news);
    void deleteNewsByName(String name);
    void deleteNewsByDate(LocalDateTime date);
}
