package com.kysorets.kisakbets.service.news;

import com.kysorets.kisakbets.model.News;
import com.kysorets.kisakbets.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImplementation implements NewsService{
    private final NewsRepository newsRepository;

    @Override
    public News getNewsByName(String name) {
        return newsRepository.getByName(name);
    }

    @Override
    public News getNewsByDate(LocalDateTime date) {
        return newsRepository.getByDate(date);
    }

    @Override
    public List<News> getNews(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var news = newsRepository.findAll(pageable);
        return news.toList();
    }

    @Override
    public void saveNews(News news) {
        newsRepository.save(news);
    }

    @Override
    public void deleteNewsByName(String name) {
        newsRepository.deleteByName(name);
    }

    @Override
    public void deleteNewsByDate(LocalDateTime date) {
        newsRepository.deleteByDate(date);
    }
}
