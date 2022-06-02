package com.kysorets.kisakbets.repository;

import com.kysorets.kisakbets.model.PurchaseDetails;
import com.kysorets.kisakbets.model.Subscription;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PurchaseDetailsRepository extends MongoRepository<PurchaseDetails, ObjectId> {
    List<PurchaseDetails> getByStatus(String status);
    PurchaseDetails getBySubscription(Subscription subscription);
    void deleteBySubscription(Subscription subscription);
}
