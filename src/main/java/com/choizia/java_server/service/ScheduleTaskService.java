package com.choizia.java_server.service;

import com.choizia.java_server.vo.HourlyStats;
import com.choizia.java_server.vo.ItemData;
import com.choizia.java_server.vo.ItemList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Service
public class ScheduleTaskService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ItemDataService itemDataService;

    @Autowired
    private ItemListService itemListService;

    @Autowired
    private HourlyStatsService hourlyStatsService;

    @Value("${lostark.api.url}")
    private String url;

    @Value("${lostark.api.token}")
    private String token;

    // DB에 응답값 저장하는 로직
    public void saveResponse(String response) {
        try {
            ItemData[] itemDataArray = objectMapper.readValue(response, ItemData[].class);
            System.out.println("Parsed ItemData array: " + Arrays.toString(itemDataArray));
            for (ItemData itemData : itemDataArray) {
                // 먼저 ItemData 저장
                itemDataService.saveItemData(itemData);

                List<HourlyStats> hourlyStatsList = itemData.getHourlyStats();
                System.out.println("Parsed hourlyStatsList object: " + hourlyStatsList);
                if (hourlyStatsList != null) {
                    for (HourlyStats hourlyStats : hourlyStatsList) {
                        hourlyStats.setItemData(itemData); // 외래 키 설정
                        hourlyStats.setStat_time(LocalTime.now());
                        System.out.println("Parsed hourlyStats object: " + hourlyStats);
                        hourlyStatsService.saveHourlyStats(hourlyStats);
                    }
                }
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

    // 한 시간 단위로 데이터를 수집하는 스케줄러
    @Scheduled(cron = "0 0 * * * ?")
    public void collectHourlyData() {
        List<ItemList> itemLists = itemListService.getAllItemList();
        for(ItemList itemList:itemLists) {
            int code = itemList.getCode();
            String response = getData(code);
            saveResponse(response);
        }
    }
}
