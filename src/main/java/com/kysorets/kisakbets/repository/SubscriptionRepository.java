package com.kysorets.kisakbets.repository;

import com.kysorets.kisakbets.model.Subscription;
import com.kysorets.kisakbets.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;

public interface SubscriptionRepository extends MongoRepository<Subscription, ObjectId> {
    Subscription getByType(String type);
    Subscription getByUser(User user);
    Subscription getByStartedAtAndEndedAt(LocalDateTime startedAt, LocalDateTime endedAt);
    void deleteByUser(User user);
}
