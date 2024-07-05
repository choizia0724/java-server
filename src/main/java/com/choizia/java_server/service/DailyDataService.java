package com.choizia.java_server.service;

import com.choizia.java_server.repository.DailyDataRepository;
import com.choizia.java_server.vo.DailyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DailyDataService {
    @Autowired
    private DailyDataRepository dailyDataRepository;

    public DailyData saveDailyData(DailyData dailyData) {
        return dailyDataRepository.saveOrUpdatePrice(dailyData);
    }

    public DailyData getDailyDataByCode(int code) {
        return dailyDataRepository.findTopByCodeOrderByCreatedAtDesc(code);
    }
}