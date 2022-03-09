package com.kysorets.kisakbets.repository;

import com.kysorets.kisakbets.model.PurchaseDetails;
import com.kysorets.kisakbets.model.Subscription;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PurchaseDetailsRepository extends MongoRepository<PurchaseDetails, String> {
    PurchaseDetails getByStatus(String status);
    PurchaseDetails getBySubscription(Subscription subscription);
    void deleteBySubscription(Subscription subscription);
}
