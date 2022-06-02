package com.kysorets.kisakbets.api;

import com.kysorets.kisakbets.model.Subscription;
import com.kysorets.kisakbets.model.User;
import com.kysorets.kisakbets.service.subscription.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    // CREATE
    @PostMapping("/subscription")
    public void createSubscription(@RequestBody Subscription subscription) {
        subscriptionService.saveSubscription(subscription);
    }

    // UPDATE
    @PutMapping("/subscription")
    public void updateSubscription(@RequestBody Subscription subscription) {
        subscriptionService.saveSubscription(subscription);
    }

    // READ BY TYPE
    @GetMapping("/subscription/{type}")
    public ResponseEntity<List<Subscription>> getSubscriptionByType(@PathVariable String type) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionByType(type));
    }

    // READ BY USER
    @GetMapping("/subscription")
    public ResponseEntity<List<Subscription>> getSubscriptionByUser(@RequestBody User user) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionByUser(user));
    }

    // READ BY STARTED AND ENDED TIME
    @GetMapping("/subscription/{started}-{ended}")
    public ResponseEntity<List<Subscription>> getSubscriptionByStartedAndEndedTime(@PathVariable LocalDateTime started,
                                                                                   @PathVariable LocalDateTime ended) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionByStartAndEndTime(started, ended));
    }

    // READ ALL
    @GetMapping("/subscriptions")
    public ResponseEntity<List<Subscription>> getAllSubscriptions(@RequestParam(required = false, defaultValue = "0") int page,
                                                  @RequestParam(required = false, defaultValue = "10") int size) {
        return ResponseEntity.ok(subscriptionService.getAllSubscriptions(page, size));
    }

    // DELETE BY USER
    @DeleteMapping("/subscription")
    public void deleteSubscriptionByUser(@RequestBody User user) {
        subscriptionService.deleteSubscriptionByUser(user);
    }
}
