package com.testdev.platform.services;

import com.testdev.platform.dao.BaseRepository;
import com.testdev.platform.domain.Bill;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BillSearch {

    @Autowired
    private BaseRepository baseRepository;

    public List<Bill> findBills(String name, String type, String url, String header){
        Bill result = this.baseRepository.findByMoreFiled()
    }

}
