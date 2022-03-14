package com.kysorets.kisakbets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Prediction")
public class Prediction {
    @Id
    private ObjectId id;
    private String type;
    private LocalDateTime date;
    private String description;

    public Prediction(String type, LocalDateTime date, String description) {
        this.type = type;
        this.date = date;
        this.description = description;
    }
}
