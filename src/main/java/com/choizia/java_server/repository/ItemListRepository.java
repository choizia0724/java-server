package com.choizia.java_server.repository;

import com.choizia.java_server.vo.ItemList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemListRepository extends JpaRepository<ItemList, Integer> {

}
