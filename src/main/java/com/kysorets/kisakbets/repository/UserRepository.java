package com.kysorets.kisakbets.repository;

import com.kysorets.kisakbets.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User getByUsername(String username);
    User getByEmail(String email);
    void deleteByUsername(String username);
    void deleteByEmail(String email);
}
