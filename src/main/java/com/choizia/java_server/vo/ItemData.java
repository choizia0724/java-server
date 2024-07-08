package com.choizia.java_server.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "item_data")
public class ItemData {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성 설정
    private Integer code;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("TradeRemainCount")
    private Integer tradeRemainCount;

    @JsonProperty("BundleCount")
    private Integer bundleCount;

    @OneToMany(mappedBy = "itemData", cascade = CascadeType.ALL)
    @JsonProperty("Stats")
    private List<HourlyStats> hourlyStats;
}
