package com.choizia.java_server.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.time.LocalTime;
import java.util.List;

@Data
@Entity
@Table(name = "item_data")
public class ItemData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer code;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("TradeRemainCount")
    private Integer tradeRemainCount;

    @JsonProperty("BundleCount")
    private Integer bundleCount;

    private LocalTime stat_time;

    @JsonProperty("Stats")
    @Type(JsonType.class)
    @Column(name = "stats", columnDefinition = "json")
    private List<Stats> stats;

    @PrePersist
    protected void onCreate() {
        this.stat_time = LocalTime.now();
    }

}
