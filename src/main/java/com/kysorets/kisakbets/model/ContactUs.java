package com.kysorets.kisakbets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ContactUs")
public class ContactUs {
    @Id
    private ObjectId id;
    private String name;
    private String email;
    private String subject;
    private String message;
    private boolean isRegistered;
    private boolean isAnswered;

    public ContactUs(String name, String email, String subject, String message, boolean isRegistered, boolean isAnswered) {
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.message = message;
        this.isRegistered = isRegistered;
        this.isAnswered = isAnswered;
    }
}
