package com.kysorets.kisakbets.service.prediction;

import com.kysorets.kisakbets.model.Prediction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public interface PredictionService {
    List<Prediction> getPredictionByType(String type);
    List<Prediction> getPredictionByDate(String date);
    Prediction getPredictionByTypeAndDate(String type, String date);
    List<Prediction> getAllPredictions(int page, int size);
    void savePrediction(Prediction prediction);
    void deletePredictionByDate(String date);
}
