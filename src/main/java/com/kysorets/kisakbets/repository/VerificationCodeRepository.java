package com.kysorets.kisakbets.repository;

import com.kysorets.kisakbets.model.User;
import com.kysorets.kisakbets.model.VerificationCode;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;

public interface VerificationCodeRepository extends MongoRepository<VerificationCode, ObjectId> {
    VerificationCode getByCode(String code);
    VerificationCode getByExpiresAt(LocalDateTime localDateTime);
    VerificationCode getByUser(User user);
    void deleteByUser(User user);
    void deleteByCode(String code);
}
