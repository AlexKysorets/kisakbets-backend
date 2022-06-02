package com.kysorets.kisakbets.api;

import com.kysorets.kisakbets.model.Prediction;
import com.kysorets.kisakbets.service.prediction.PredictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    // CREATE
    @PostMapping("/prediction")
    public void createPrediction(@RequestBody Prediction prediction) {
        predictionService.savePrediction(prediction);
    }

    // UPDATE
    @PutMapping("/prediction")
    public void updatePrediction(@RequestBody Prediction prediction) {
        predictionService.savePrediction(prediction);
    }

    // READ BY TYPE OR DATE
    @GetMapping("/prediction/{field}")
    public ResponseEntity<List<Prediction>> getPredictionByTypeOrDate(@PathVariable String field) throws ParseException {
        if (DATE_PATTERN.matcher(field).matches()) {
            return ResponseEntity.ok(predictionService.getPredictionByDate(field));
        } else
            return ResponseEntity.ok(predictionService.getPredictionByType(field));
    }

    // READ ALL
    @GetMapping("/predictions")
    public ResponseEntity<List<Prediction>> getAllPredictions(@RequestParam(required = false, defaultValue = "0") int page,
                                                              @RequestParam(required = false, defaultValue = "10") int size) {
        return ResponseEntity.ok(predictionService.getAllPredictions(page, size));
    }

    // DELETE BY DATE
    @DeleteMapping("/prediction/{date}")
    public void deletePredictionByDate(@PathVariable String date) {
        predictionService.deletePredictionByDate(date);
    }
}
