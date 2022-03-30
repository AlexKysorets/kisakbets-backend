package com.kysorets.kisakbets.repository;

import com.kysorets.kisakbets.model.ContactUs;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactUsRepository extends MongoRepository<ContactUs, ObjectId> {
    ContactUs getByEmail(String email);
    ContactUs getBySubject(String subject);
    void deleteByEmail(String email);
}
