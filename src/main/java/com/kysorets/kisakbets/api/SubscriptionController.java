package com.kysorets.kisakbets.api;

import com.kysorets.kisakbets.model.Subscription;
import com.kysorets.kisakbets.model.User;
import com.kysorets.kisakbets.service.subscription.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    // CREATE AND UPDATE
    @PostMapping("/subscription")
    public void createOrUpdateSubscription(@RequestBody Subscription subscription) {
        subscriptionService.saveSubscription(subscription);
    }

    // READ BY TYPE
    @GetMapping("/subscription/{type}")
    public Subscription getSubscriptionByType(@PathVariable String type) {
        return subscriptionService.getSubscriptionByType(type);
    }

    // READ BY USER
    @GetMapping("/subscription")
    public Subscription getSubscriptionByUser(@RequestBody User user) {
        return subscriptionService.getSubscriptionByUser(user);
    }

    // READ BY STARTED AND ENDED TIME
    @GetMapping("/subscription/{started}-{ended}")
    public Subscription getSubscriptionByStartedAndEndedTime(@PathVariable LocalDateTime started, @PathVariable LocalDateTime ended) {
        return subscriptionService.getSubscriptionByStartAndEndTime(started, ended);
    }

    // READ ALL
    @GetMapping("/subscriptions")
    public List<Subscription> getAllSubscriptions() {
        return subscriptionService.getAllSubscriptions();
    }

    // DELETE BY USER
    @DeleteMapping("/subscription")
    public void deleteSubscriptionByUser(@RequestBody User user) {
        subscriptionService.deleteSubscriptionByUser(user);
    }
}
