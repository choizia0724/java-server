package com.choizia.java_server.service;

import com.choizia.java_server.vo.DailyData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ScheduleTaskService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    DailyDataService dailyDataService;

    @Value("${lostark.api.url}")
    private String url;

    @Value("${lostark.api.token}")
    private String token;

    // DB에 응답값 저장하는 로직
    public void saveResponse(String response) {
        try {
            System.out.println("Received JSON response: " + response);
            List<DailyData> dailyDataList = objectMapper.readValue(response, new TypeReference<List<DailyData>>(){});

            for (DailyData dailyData : dailyDataList) {
                System.out.println("Parsed DailyData object: " + dailyData);
                dailyDataService.saveDailyData(dailyData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // LostArk API 에서 데이터 가져오는 로직
    public String getData(int code) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url.concat(Integer.toString(code)), HttpMethod.GET, entity, String.class);

        return response.getBody();
    }
}
