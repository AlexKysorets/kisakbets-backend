package com.kysorets.kisakbets.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kysorets.kisakbets.model.Stats;
import com.kysorets.kisakbets.service.stats.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stats")
public class StatisticsController {
    private final StatsService statsService;
    private final HttpServletResponse response;

    @GetMapping("/current-month")
    public void getStatsForCurrentMonth() throws IOException {
        LocalDateTime date = LocalDateTime.now();
        String parseMonth = date.getMonth().toString().toLowerCase();
        String currentMonth = parseMonth.substring(0, 1).toUpperCase() + parseMonth.substring(1);
        String currentYear = String.valueOf(date.getYear());
        Stats statsSingle = statsService.getStatsByNameAndType(currentMonth + " " + currentYear, "single");
        Stats statsExpress = statsService.getStatsByNameAndType(currentMonth + " " + currentYear, "express");

        List<Stats> stats = new ArrayList<>();
        stats.add(statsSingle);
        stats.add(statsExpress);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), stats);
    }
}
