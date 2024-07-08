package com.choizia.java_server.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "hourly_stats")
public class HourlyStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성 설정
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "item_data_code") // 외래 키 설정
    private ItemData itemData;

    @JsonProperty("Date")
    private LocalDate stat_date;

    private LocalTime stat_time;

    @JsonProperty("TradeCount")
    private Integer stat_trade_count;

    @JsonProperty("AvgPrice")
    private String stat_price;
}
