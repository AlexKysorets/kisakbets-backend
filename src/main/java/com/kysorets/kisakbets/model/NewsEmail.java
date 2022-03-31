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
@Document(collection = "NewsEmail")
public class NewsEmail {
    @Id
    private ObjectId id;
    private String email;

    public NewsEmail(String email) {
        this.email = email;
    }
}
