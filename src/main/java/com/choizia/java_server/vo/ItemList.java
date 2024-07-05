package com.choizia.java_server.vo;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "item_list")
public class ItemList {
    @Id
    private Integer code;

    private String name;

    private Boolean makeable;
}
