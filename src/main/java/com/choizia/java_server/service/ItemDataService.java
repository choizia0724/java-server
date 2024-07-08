package com.choizia.java_server.service;

import com.choizia.java_server.repository.ItemDataRepository;
import com.choizia.java_server.vo.ItemData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemDataService {

    @Autowired
    private ItemDataRepository itemDataRepository;

    public void saveItemData(ItemData itemData) {
        itemDataRepository.save(itemData);
    }
}

