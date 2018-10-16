package com.testdev.platform.services;

import com.testdev.platform.dao.BaseRepository;
import com.testdev.platform.domain.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

@Repository
public class BillSearch {

    @Autowired
    private BaseRepository baseRepository;

    public List<Bill> findBills(String name, String type, String url, String header){
        LinkedHashMap<String,Object> map = null;
        map.put("name", name);
        map.put("type", type);
        map.put("url", url);
        map.put("header", header);
//        if (name != null && name !=""){
//            map.put("name",name);
//        }
//        if (type != null && type != ""){
//            map.put("type", type);
//        }
//        if (url != null && url != ""){
//            map.put("url", url);
//        }
//        if (header != null && header != ""){
//            map.put("header", header);
//        }
        List<Bill> result = this.baseRepository.findByMoreFiled("interface", map);
        return result;
    }

}
