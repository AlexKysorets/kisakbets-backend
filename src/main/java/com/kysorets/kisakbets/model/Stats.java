package com.kysorets.kisakbets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Stats")
public class Stats {
    @Id
    private ObjectId id;
    private String name;
    private String type;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private int winCount;
    private int loseCount;
    private int refundCount;
    private double winPercent;
    private double minCoef;
    private double maxCoef;
    private double averageCoef;
    private int profit;
    private double profitPercent;
    @DocumentReference
    private List<Prediction> predictions;

    public Stats(String name, String type, LocalDateTime startedAt, LocalDateTime endedAt, int winCount, int loseCount,
                 int refundCount, double winPercent, double minCoef, double maxCoef, double averageCoef, int profit,
                 double profitPercent, List<Prediction> predictions) {
        this.name = name;
        this.type = type;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.winCount = winCount;
        this.loseCount = loseCount;
        this.refundCount = refundCount;
        this.winPercent = winPercent;
        this.minCoef = minCoef;
        this.maxCoef = maxCoef;
        this.averageCoef = averageCoef;
        this.profit = profit;
        this.profitPercent = profitPercent;
        this.predictions = predictions;
    }
}
