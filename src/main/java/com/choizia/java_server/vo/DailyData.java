package com.choizia.java_server.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Entity
@Table(name = "daily_data")
public class DailyData {

    @Id
    @JsonProperty("date")
    private Instant stat_date;

    @Id
    private Integer code;

    @JsonProperty("TradeCount")
    private Integer trade_count;

    @JsonProperty("AvgPrice")
    private String stat_price;


}

