package com.kysorets.kisakbets.repository;

import com.kysorets.kisakbets.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, Integer> {
    Role getByName(String name);
}
