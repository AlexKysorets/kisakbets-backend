package com.kysorets.kisakbets.repository;

import com.kysorets.kisakbets.model.ContactUs;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ContactUsRepository extends MongoRepository<ContactUs, ObjectId> {
    List<ContactUs> getByEmail(String email);
    List<ContactUs> getBySubject(String subject);
    void deleteByEmail(String email);
}
