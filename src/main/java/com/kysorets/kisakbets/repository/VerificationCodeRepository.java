package com.kysorets.kisakbets.repository;

import com.kysorets.kisakbets.model.User;
import com.kysorets.kisakbets.model.VerificationCode;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;

public interface VerificationCodeRepository extends MongoRepository<VerificationCode, String> {
    VerificationCode getByCode(String code);
    VerificationCode getByExpiresAt(LocalDateTime localDateTime);
    VerificationCode getByUser(User user);
    void deleteByUser(User user);
    void deleteByCode(String code);
}
