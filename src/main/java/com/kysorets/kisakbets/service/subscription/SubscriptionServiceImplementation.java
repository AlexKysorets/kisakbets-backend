package com.kysorets.kisakbets.service.subscription;

import com.kysorets.kisakbets.model.Subscription;
import com.kysorets.kisakbets.model.User;
import com.kysorets.kisakbets.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImplementation implements SubscriptionService{
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public List<Subscription> getSubscriptionByType(String type) {
        return subscriptionRepository.getByType(type);
    }

    @Override
    public List<Subscription> getSubscriptionByUser(User user) {
        return subscriptionRepository.getByUser(user);
    }

    @Override
    public List<Subscription> getSubscriptionByStartAndEndTime(LocalDateTime startedAt, LocalDateTime endedAt) {
        return subscriptionRepository.getByStartedAtAndEndedAt(startedAt, endedAt);
    }

    @Override
    public Subscription getSubscriptionByUserAndType(User user, String type) {
        return subscriptionRepository.getByUserAndType(user, type);
    }

    @Override
    public List<Subscription> getAllSubscriptions(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var subscriptions = subscriptionRepository.findAll(pageable);
        return subscriptions.toList();
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
