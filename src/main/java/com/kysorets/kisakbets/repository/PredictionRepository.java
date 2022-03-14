package com.kysorets.kisakbets.repository;

import com.kysorets.kisakbets.model.Prediction;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;

public interface PredictionRepository extends MongoRepository<Prediction, ObjectId> {
    Prediction getByType(String type);
    Prediction getByDate(LocalDateTime date);
    void deleteByDate(LocalDateTime date);
}
