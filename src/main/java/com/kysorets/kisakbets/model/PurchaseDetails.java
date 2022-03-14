package com.kysorets.kisakbets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "PurchaseDetails")
public class PurchaseDetails {
    @Id
    private ObjectId id;
    @DocumentReference
    private Subscription subscription;
    private String status;

    public PurchaseDetails(Subscription subscription, String status) {
        this.subscription = subscription;
        this.status = status;
    }
}
