package com.choizia.java_server.service;

import org.springframework.stereotype.Service;

@Service
public class DataService {

    public void saveResponse(String response) {
        // 원격 DB에 응답값 저장 로직 구현
    }

    public String getData() {
        // 로스트아크 api로부터 데이터 가져오는 로직
        return "Data from api";
    }
}
