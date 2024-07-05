package com.choizia.java_server.service;

import com.choizia.java_server.repository.ItemListRepository;
import com.choizia.java_server.vo.ItemList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemListService{

    @Autowired
    private ItemListRepository itemListRepository;

    public List<ItemList> getAllItemList(){
       return itemListRepository.findAll();
    }
}

