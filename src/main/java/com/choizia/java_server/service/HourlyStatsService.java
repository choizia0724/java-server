package com.choizia.java_server.service;

import com.choizia.java_server.vo.HourlyStats;
import com.choizia.java_server.repository.HourlyStatsRepository;
import com.choizia.java_server.vo.ItemData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HourlyStatsService {

    @Autowired
    private HourlyStatsRepository hourlyStatsRepository;

    public void saveHourlyStats(HourlyStats hourlyStats) {
        hourlyStatsRepository.save(hourlyStats);
    }

    public List<HourlyStats> findHourlyStatsByItemData(ItemData itemData) {
        return hourlyStatsRepository.findAllByItemData(itemData);
    }
}
