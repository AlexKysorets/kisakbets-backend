package com.kysorets.kisakbets.repository;

import com.kysorets.kisakbets.model.Stats;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsRepository extends MongoRepository<Stats, ObjectId> {
    List<Stats> getByName(String name);
    List<Stats> getByType(String type);
    List<Stats> getByStartedAtAndEndedAt(LocalDateTime startedAt, LocalDateTime endedAt);
    Stats getByNameAndType(String name, String type);
    void deleteByName(String name);
}
