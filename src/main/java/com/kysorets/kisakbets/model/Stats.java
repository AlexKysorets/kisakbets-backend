package com.kysorets.kisakbets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Stats")
public class Stats {
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
}
