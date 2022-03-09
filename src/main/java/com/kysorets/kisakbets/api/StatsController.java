package com.kysorets.kisakbets.api;

import com.kysorets.kisakbets.model.Stats;
import com.kysorets.kisakbets.service.stats.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StatsController {
    private final StatsService statsService;

    // CREATE AND UPDATE
    @PostMapping("/stat")
    public void createOrUpdateStats(@RequestBody Stats stats) {
        statsService.saveStats(stats);
    }

    // READ BY TYPE
    @GetMapping("/stat/{type}")
    public Stats getStatsByType(@PathVariable String type) {
        return statsService.getStatsByType(type);
    }

    // READ BY STARTED AND ENDED TIME
    @GetMapping("/stat/{startedAt}-{endedAt}")
    public Stats getStatsByStartedAndEndedTime(@PathVariable LocalDateTime startedAt, @PathVariable LocalDateTime endedAt) {
        return statsService.getStatsByStartedAndEndedTime(startedAt, endedAt);
    }

    // READ ALL
    @GetMapping("/stats")
    public List<Stats> getAllStats() {
        return statsService.getAllStats();
    }

    // DELETE BY NAME
    @DeleteMapping("/stat/{name}")
    public void deleteStatsByName(@PathVariable String name) {
        statsService.deleteStatsByName(name);
    }
}
