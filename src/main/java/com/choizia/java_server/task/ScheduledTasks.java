package com.choizia.java_server.task;

import com.choizia.java_server.repository.ItemListRepository;
import com.choizia.java_server.service.ItemListService;
import com.choizia.java_server.service.ScheduleTaskService;
import com.choizia.java_server.vo.ItemList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ScheduledTasks {

    @Autowired
    private ScheduleTaskService scheduleTaskService;

    @Autowired
    private ItemListService itemListService;

    @Scheduled(cron = "0 * * * * ?")
    public void performTask() {
        List<ItemList> itemLists = itemListService.getAllItemList();
        for(ItemList itemList:itemLists){
            scheduleTaskService.saveResponse(scheduleTaskService.getData(itemList.getCode()));
        }
    }
}