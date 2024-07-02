package com.choizia.java_server.controller;

import com.choizia.java_server.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class DataController {

    @Autowired
    private DataService dataService;

    @GetMapping("/data")
    public String getData() {
        return dataService.getData();
    }
}