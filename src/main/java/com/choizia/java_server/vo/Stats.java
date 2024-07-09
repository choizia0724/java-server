package com.choizia.java_server.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Stats {

    @JsonProperty("Date")
    private LocalDate stat_date;

    @JsonProperty("TradeCount")
    private Integer stat_trade_count;

    @JsonProperty("AvgPrice")
    private String stat_price;
}
