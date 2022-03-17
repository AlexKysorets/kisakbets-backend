package com.kysorets.kisakbets.service.subscription;

import com.kysorets.kisakbets.model.Subscription;
import com.kysorets.kisakbets.model.User;
import com.kysorets.kisakbets.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImplementation implements SubscriptionService{
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription getSubscriptionByType(String type) {
        return subscriptionRepository.getByType(type);
    }

    @Override
    public Subscription getSubscriptionByUser(User user) {
        return subscriptionRepository.getByUser(user);
    }

    @Override
    public Subscription getSubscriptionByStartAndEndTime(LocalDateTime startedAt, LocalDateTime endedAt) {
        return subscriptionRepository.getByStartedAtAndEndedAt(startedAt, endedAt);
    }

    @Override
    public Subscription getSubscriptionByUserAndType(User user, String type) {
        return subscriptionRepository.getByUserAndType(user, type);
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    @Override
    public void saveSubscription(Subscription subscription) {
        subscriptionRepository.save(subscription);
    }

    @Override
    public void deleteSubscriptionByUser(User user) {
        subscriptionRepository.deleteByUser(user);
    }
}
