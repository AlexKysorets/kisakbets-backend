package com.kysorets.kisakbets.service.stats;

import com.kysorets.kisakbets.model.Stats;
import com.kysorets.kisakbets.repository.StatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsServiceImplementation implements StatsService{
    private final StatsRepository statsRepository;

    @Override
    public Stats getStatsByName(String name) {
        return statsRepository.getByName(name);
    }

    @Override
    public Stats getStatsByType(String type) {
        return statsRepository.getByType(type);
    }

    @Override
    public Stats getStatsByStartedAndEndedTime(LocalDateTime startedAt, LocalDateTime endedAt) {
        return statsRepository.getByStartedAtAndEndedAt(startedAt, endedAt);
    }

    @Override
    public Stats getStatsByNameAndType(String name, String type) {
        return statsRepository.getByNameAndType(name, type);
    }

    @Override
    public List<Stats> getAllStats() {
        return statsRepository.findAll();
    }

    @Override
    public void saveStats(Stats stats) {
        statsRepository.save(stats);
    }

    @Override
    public void deleteStatsByName(String name) {
        statsRepository.deleteByName(name);
    }
}
