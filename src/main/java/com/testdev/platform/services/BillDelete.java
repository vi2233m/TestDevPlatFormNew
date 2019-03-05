package com.testdev.platform.services;

import com.testdev.platform.dao.BaseRepository;
import com.testdev.platform.domain.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;

@Repository
public class BillDelete {

    @Autowired
    private BaseRepository baseRepository;
    public boolean billDelete(Bill bill){
        boolean isTure = this.baseRepository.delete(bill);
        return isTure;
    }


    public Object findById(Bill bill, int id){

        return this.baseRepository.findByid(bill,id);
    }
}
