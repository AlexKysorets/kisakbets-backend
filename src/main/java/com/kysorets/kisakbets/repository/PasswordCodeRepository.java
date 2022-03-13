package com.kysorets.kisakbets.repository;

import com.kysorets.kisakbets.model.PasswordCode;
import com.kysorets.kisakbets.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;

public interface PasswordCodeRepository extends MongoRepository<PasswordCode, ObjectId> {
    PasswordCode getByCode(String code);
    PasswordCode getByExpiresAt(LocalDateTime date);
    PasswordCode getByUser(User user);
    void deleteByUser(User user);
    void deleteByCode(String code);
}
