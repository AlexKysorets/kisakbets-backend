package com.kysorets.kisakbets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Prediction")
public class Prediction {
    @Id
    private ObjectId id;
    private String type;
    private String date;
    private String description;

    public Prediction(String type, String date, String description) {
        this.type = type;
        this.date = date;
        this.description = description;
    }
}
