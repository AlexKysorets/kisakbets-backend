package com.kysorets.kisakbets.service.prediction;

import com.kysorets.kisakbets.model.Prediction;
import com.kysorets.kisakbets.repository.PredictionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public Prediction getPredictionByDate(LocalDateTime date) {
        return predictionRepository.getByDate(date);
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
    public void deletePredictionByDate(LocalDateTime date) {
        predictionRepository.deleteByDate(date);
    }
}
