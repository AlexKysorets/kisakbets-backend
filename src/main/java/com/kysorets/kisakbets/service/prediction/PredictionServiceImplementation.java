package com.kysorets.kisakbets.service.prediction;

import com.kysorets.kisakbets.model.Prediction;
import com.kysorets.kisakbets.repository.PredictionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PredictionServiceImplementation implements PredictionService{
    private final PredictionRepository predictionRepository;

    @Override
    public Prediction getPredictionByType(String type) {
        return predictionRepository.getByType(type);
    }

    @Override
    public Prediction getPredictionByDate(String date) {
        return predictionRepository.getByDate(date);
    }

    @Override
    public Prediction getPredictionByTypeAndDate(String type, String date) {
        return predictionRepository.getByTypeAndDate(type, date);
    }

    @Override
    public List<Prediction> getAllPredictions() {
        return predictionRepository.findAll();
    }

    @Override
    public void savePrediction(Prediction prediction) {
        predictionRepository.save(prediction);
    }

    @Override
    public void deletePredictionByDate(String date) {
        predictionRepository.deleteByDate(date);
    }
}
