package com.kysorets.kisakbets.repository;

import com.kysorets.kisakbets.model.Prediction;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface PredictionRepository extends MongoRepository<Prediction, ObjectId> {
    List<Prediction> getByType(String type);
    List<Prediction> getByDate(String date);
    Prediction getByTypeAndDate(String type, String date);
    void deleteByDate(String date);
}
