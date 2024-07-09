package com.choizia.java_server.service;

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
    private ItemListService itemListService;

    @Autowired
    private ItemDataService itemDataService;

    @Value("${lostark.api.url}")
    private String url;

    @Value("${lostark.api.token}")
    private String token;

    // DB에 응답값 저장하는 로직
    public void saveResponse(String response,int code) {
        try {
            ItemData[] itemDataArray = objectMapper.readValue(response, ItemData[].class);

            for (ItemData itemData : itemDataArray) {
                itemData.setCode(code);
                if(itemData.getTradeRemainCount()==null){
                    itemData.setTradeRemainCount(1);
                }
                itemData.setStat_time(LocalTime.now());
                itemDataService.saveItemData(itemData);
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
            saveResponse(response,code);
        }
    }
}
