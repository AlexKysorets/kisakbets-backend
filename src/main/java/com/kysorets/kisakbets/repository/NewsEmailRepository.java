package com.kysorets.kisakbets.repository;

import com.kysorets.kisakbets.model.NewsEmail;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewsEmailRepository extends MongoRepository<NewsEmail, ObjectId> {
    NewsEmail getByEmail(String email);
    void deleteByEmail(String email);
}
