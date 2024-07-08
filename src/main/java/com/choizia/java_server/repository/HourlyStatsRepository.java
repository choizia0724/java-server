package com.choizia.java_server.repository;

import com.choizia.java_server.vo.HourlyStats;
import com.choizia.java_server.vo.ItemData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HourlyStatsRepository extends JpaRepository<HourlyStats, Integer> {
    List<HourlyStats> findAllByItemData(ItemData itemData);
}
