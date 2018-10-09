package com.testdev.platform.controller;

import com.testdev.platform.domain.Bill;
import com.testdev.platform.services.BillRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestJpaController {


//    @Autowired
//    private BillRepositroy billRepository;
//    @RequestMapping("/list")
//    @ResponseBody
//    public List<Bill> List(Model model){
//
//        //String sql="select o.type,o.url from interface o where o.id= ? ";
//        // Integer userId=1;
//        //Page<Map> orderLists = billRepository.findPageByParams(sql,new PageRequest(1,10),userId);
//        List<Bill> result = this.billRepository.findAll(new Sort(new Sort.Order(Sort.Direction.DESC,"id")));
//        model.addAttribute("bills",result);
//        return result;
//    }
}
