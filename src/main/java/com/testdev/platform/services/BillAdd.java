package com.testdev.platform.services;

import com.testdev.platform.dao.BaseRepository;
import com.testdev.platform.domain.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BillAdd {

    @Autowired
    private BaseRepository baseRepository;

    /**
     * b保存bill 实体
     * @return
     */
    public boolean billSave(Bill bill){
        boolean isTure = this.baseRepository.save(bill);
        return isTure;
    }
}
