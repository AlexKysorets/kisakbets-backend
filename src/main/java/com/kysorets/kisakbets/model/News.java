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
@Document(collection = "News")
public class News {
    @Id
    private ObjectId id;
    private String name;
    private String content;
    private LocalDateTime date;

    public News(String name, String content, LocalDateTime date) {
        this.name = name;
        this.content = content;
        this.date = date;
    }
}
