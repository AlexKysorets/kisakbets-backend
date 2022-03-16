package com.kysorets.kisakbets.service.prediction;

import com.kysorets.kisakbets.model.Prediction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public interface PredictionService {
    Prediction getPredictionByType(String type);
    Prediction getPredictionByDate(String date);
    Prediction getPredictionByTypeAndDate(String type, String date);
    List<Prediction> getAllPredictions();
    void savePrediction(Prediction prediction);
    void deletePredictionByDate(String date);
}
