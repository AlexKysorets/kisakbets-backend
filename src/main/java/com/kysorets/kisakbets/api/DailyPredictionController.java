package com.kysorets.kisakbets.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kysorets.kisakbets.model.Prediction;
import com.kysorets.kisakbets.model.Subscription;
import com.kysorets.kisakbets.model.User;
import com.kysorets.kisakbets.service.prediction.PredictionService;
import com.kysorets.kisakbets.service.subscription.SubscriptionService;
import com.kysorets.kisakbets.service.user.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/daily-prediction")
public class DailyPredictionController {
    private final PredictionService predictionService;
    private final UserService userService;
    private final SubscriptionService subscriptionService;
    private final HttpServletResponse response;

    @GetMapping("/single")
    public void getDailySinglePrediction (@RequestBody DailyPredictionInfo info) throws IOException {
        User user = userService.getUserByUsername(info.getUsername());
        Subscription subscription = subscriptionService.getSubscriptionByUserAndType(user, "single");
        if (subscription != null) {
            if (subscription.getEndedAt().isAfter(LocalDateTime.now())) {
                String pattern = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String date = simpleDateFormat.format(new Date());
                Prediction prediction = predictionService.getPredictionByTypeAndDate("single", date);
                if (prediction != null) {
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), prediction);
                } else {
                    Map<String, String> result = new HashMap<>();
                    result.put("message", "Prediction isn't ready for now!");
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), result);
                }
            } else {
                Map<String, String> errors = new HashMap<>();
                errors.put("error", "User don't have current subscription!");
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), errors);
            }
        } else {
            Map<String, String> errors = new HashMap<>();
            errors.put("error", "User don't have any subscription!");
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), errors);
        }
    }

    @GetMapping("/express")
    public void getDailyExpressPrediction(@RequestBody DailyPredictionInfo info) throws IOException {
        User user = userService.getUserByUsername(info.getUsername());
        Subscription subscription = subscriptionService.getSubscriptionByUserAndType(user, "express");
        if (subscription != null) {
            if (subscription.getEndedAt().isAfter(LocalDateTime.now())) {
                String pattern = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String date = simpleDateFormat.format(new Date());
                Prediction prediction = predictionService.getPredictionByTypeAndDate("express", date);
                if (prediction != null) {
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), prediction);
                } else {
                    Map<String, String> result = new HashMap<>();
                    result.put("message", "Prediction isn't ready for now!");
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), result);
                }
            } else {
                Map<String, String> errors = new HashMap<>();
                errors.put("error", "User don't have current subscription!");
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), errors);
            }
        } else {
            Map<String, String> errors = new HashMap<>();
            errors.put("error", "User don't have any subscription!");
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), errors);
        }
    }
}

@Data
class DailyPredictionInfo {
    private String username;
}