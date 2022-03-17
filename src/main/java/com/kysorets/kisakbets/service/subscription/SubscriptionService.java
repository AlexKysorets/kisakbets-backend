package com.kysorets.kisakbets.service.subscription;

import com.kysorets.kisakbets.model.Subscription;
import com.kysorets.kisakbets.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface SubscriptionService {
    Subscription getSubscriptionByType(String type);
    Subscription getSubscriptionByUser(User user);
    Subscription getSubscriptionByStartAndEndTime(LocalDateTime startedAt, LocalDateTime endedAt);
    Subscription getSubscriptionByUserAndType(User user, String type);
    List<Subscription> getAllSubscriptions();
    void saveSubscription(Subscription subscription);
    void deleteSubscriptionByUser(User user);
}
