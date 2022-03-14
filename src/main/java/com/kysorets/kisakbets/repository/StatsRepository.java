package com.kysorets.kisakbets.repository;

import com.kysorets.kisakbets.model.Stats;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;

public interface StatsRepository extends MongoRepository<Stats, ObjectId> {
    Stats getByName(String name);
    Stats getByType(String type);
    Stats getByStartedAtAndEndedAt(LocalDateTime startedAt, LocalDateTime endedAt);
    void deleteByName(String name);
}
