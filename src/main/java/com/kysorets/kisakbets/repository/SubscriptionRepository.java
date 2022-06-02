package com.kysorets.kisakbets.repository;

import com.kysorets.kisakbets.model.Subscription;
import com.kysorets.kisakbets.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SubscriptionRepository extends MongoRepository<Subscription, ObjectId> {
    List<Subscription> getByType(String type);
    List<Subscription> getByUser(User user);
    List<Subscription> getByStartedAtAndEndedAt(LocalDateTime startedAt, LocalDateTime endedAt);
    Subscription getByUserAndType(User user, String type);
    void deleteByUser(User user);
}
