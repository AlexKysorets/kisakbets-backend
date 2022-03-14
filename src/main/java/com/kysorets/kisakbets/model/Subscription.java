package com.kysorets.kisakbets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Subscription")
public class Subscription {
    @Id
    private ObjectId id;
    private String type;
    @DocumentReference
    private User user;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    public Subscription(String type, User user, LocalDateTime startedAt, LocalDateTime endedAt) {
        this.type = type;
        this.user = user;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }
}
