package com.kysorets.kisakbets.repository;

import com.kysorets.kisakbets.model.News;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;

public interface NewsRepository extends MongoRepository<News, ObjectId> {
    News getByName(String name);
    News getByDate(LocalDateTime date);
    void deleteByName(String name);
    void deleteByDate(LocalDateTime date);
}
