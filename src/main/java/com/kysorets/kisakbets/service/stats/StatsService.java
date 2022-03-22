package com.kysorets.kisakbets.service.stats;

import com.kysorets.kisakbets.model.Stats;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface StatsService {
    Stats getStatsByName(String name);
    Stats getStatsByType(String type);
    Stats getStatsByStartedAndEndedTime(LocalDateTime startedAt, LocalDateTime endedAt);
    Stats getStatsByNameAndType(String name, String type);
    List<Stats> getAllStats();
    void saveStats(Stats stats);
    void deleteStatsByName(String name);
}
