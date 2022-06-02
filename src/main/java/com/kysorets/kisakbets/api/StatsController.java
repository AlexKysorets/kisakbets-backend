package com.kysorets.kisakbets.api;

import com.kysorets.kisakbets.model.Stats;
import com.kysorets.kisakbets.service.stats.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StatsController {
    private final StatsService statsService;

    // CREATE
    @PostMapping("/stat")
    public void createStats(@RequestBody Stats stats) {
        statsService.saveStats(stats);
    }

    // UPDATE
    @PutMapping("/stat")
    public void updateStats(@RequestBody Stats stats) {
        statsService.saveStats(stats);
    }

    // READ BY TYPE
    @GetMapping("/stat/{type}")
    public ResponseEntity<List<Stats>> getStatsByType(@PathVariable String type) {
        return ResponseEntity.ok(statsService.getStatsByType(type));
    }

    // READ BY STARTED AND ENDED TIME
    @GetMapping("/stat/{startedAt}-{endedAt}")
    public ResponseEntity<List<Stats>> getStatsByStartedAndEndedTime(@PathVariable LocalDateTime startedAt,
                                                                     @PathVariable LocalDateTime endedAt) {
        return ResponseEntity.ok(statsService.getStatsByStartedAndEndedTime(startedAt, endedAt));
    }

    // READ ALL
    @GetMapping("/stats")
    public ResponseEntity<List<Stats>> getAllStats(@RequestParam(required = false, defaultValue = "0") int page,
                                                   @RequestParam(required = false, defaultValue = "10") int size) {
        return ResponseEntity.ok(statsService.getAllStats(page, size));
    }

    // DELETE BY NAME
    @DeleteMapping("/stat/{name}")
    public void deleteStatsByName(@PathVariable String name) {
        statsService.deleteStatsByName(name);
    }
}
