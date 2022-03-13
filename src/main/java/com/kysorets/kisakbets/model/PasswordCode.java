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
@Document(collection = "PasswordCode")
public class PasswordCode {
    @Id
    private ObjectId id;
    private String code;
    private LocalDateTime expiresAt;
    @DocumentReference
    private User user;

    public PasswordCode(String code, LocalDateTime expiresAt, User user) {
        this.code = code;
        this.expiresAt = expiresAt;
        this.user = user;
    }
}
