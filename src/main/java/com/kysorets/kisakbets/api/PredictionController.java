package com.kysorets.kisakbets.api;

import com.kysorets.kisakbets.model.Prediction;
import com.kysorets.kisakbets.service.prediction.PredictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PredictionController {
    private final PredictionService predictionService;
    private final static Pattern DATE_PATTERN = Pattern.compile(
            "^\\d{4}-\\d{2}-\\d{2}$");

    // CREATE AND UPDATE
    @PostMapping("/prediction")
    public void createOrUpdatePrediction(@RequestBody Prediction prediction) {
        predictionService.savePrediction(prediction);
    }

    // READ BY TYPE OR DATE
    @GetMapping("/prediction/{field}")
    public Prediction getPredictionByTypeOrDate(@PathVariable String field) throws ParseException {
        if (DATE_PATTERN.matcher(field).matches()) {
            return predictionService.getPredictionByDate(field);
        } else
            return predictionService.getPredictionByType(field);
    }

    // READ ALL
    @GetMapping("/predictions")
    public List<Prediction> getAllPredictions() {
        return predictionService.getAllPredictions();
    }

    // DELETE BY DATE
    @DeleteMapping("/prediction/{date}")
    public void deletePredictionByDate(@PathVariable String date) {
        predictionService.deletePredictionByDate(date);
    }
}
