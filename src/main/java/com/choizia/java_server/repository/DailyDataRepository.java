package com.choizia.java_server.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;


@Repository
public class DailyDataRepository  {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveOrUpdatePrice(int code, LocalDate date, BigDecimal price) {
        String sql = "INSERT INTO prices (code, date, price) VALUES (?, ?, ?) " +
                "ON CONFLICT (code, date) DO UPDATE SET price = EXCLUDED.price";
        jdbcTemplate.update(sql, code, date, price);
    }
}
