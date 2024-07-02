package com.choizia.java_server.task;

import com.choizia.java_server.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



@Component
public class ScheduledTasks {

    @Autowired
    private DataService dataService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void performTask() {
        String url = ""; // LostArk API URL
        String token = "";  // Token

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        dataService.saveResponse(response.getBody());
    }
}