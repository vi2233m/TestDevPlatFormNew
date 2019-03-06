package com.testdev.platform.services;

import com.testdev.platform.dao.BaseRepository;
import com.testdev.platform.domain.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BillSearch {

    @Autowired
    private BaseRepository baseRepository;

    /**
     * 返回查询条件数据列表
     * @param map
     * @return
     */
    public List<Bill> findBills(LinkedHashMap<String,Object> map){
        List<Bill> result = this.baseRepository.findByMoreFiled("Bill", map);
        return result;
    }

    /**
     * 返回查询结果分页
     * @param map
     * @param start
     * @param pageSize
     * @return
     */
    public List<Bill> findBillPage(LinkedHashMap<String,Object> map, int start, int pageSize){
        List<Bill> result = this.baseRepository.findByMoreFiledpages("Bill", map, start, pageSize);
        return result;
    }

    /**
     * 返回查询条件总条数
     * @param map
     * @return
     */
    public Object findCounts(LinkedHashMap<String,Object> map){
        Object result = this.baseRepository.findCount("Bill",map);
        return result;
    }

    /**
     * 更新数据
     * @param bill
     * @return
     */
    public boolean updateBill(Bill bill){
        boolean isSuccess = this.baseRepository.update(bill);
        return isSuccess;
    }
}
