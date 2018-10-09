package com.testdev.platform.controller;

import com.testdev.platform.dao.BillDao;
import com.testdev.platform.domain.Bill;
import com.testdev.platform.services.BillRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class BillListController {

    @RequestMapping("/billAdd")
    public String billAdd(){
        return "billAdd";
    }

    //根据ID 查询出具体的对象信息
    @Autowired
    private BillRepositroy billRepository;
    @RequestMapping("/interface/{id}")
    public String billView(@PathVariable ("id") int id){
        return "formelements";
    }

    @Autowired
    private BillDao billDao;
    @RequestMapping(value = "/interface/search", method = RequestMethod.POST)
//  @PathVariable("name") String name, @PathVariable("type") String type, @PathVariable("url") String url, @PathVariable("header") String header
    public String billSearch(@RequestParam(value ="name") String name, @RequestParam("type") String type, @RequestParam("url") String url, @RequestParam("header") String header, Model model) {
        //BillDao bd = new BillDao();
        System.out.println("name: " + name + " type: " + type + " url: " + url + " header: " + header);
        Bill result = this.billDao.searchBillByEm(name, type, url, header, 0, 10);
        model.addAttribute("results", result);
        return "tables1";
    }


    @RequestMapping("/billUpdate")
    public String billUpdate(){
        return "billUpdate";
    }


//    @Autowired
//    private BillRepositroy billRepository;
//    @RequestMapping("/billList")
//    @ResponseBody
//    public List<Bill> billList(){
//
//        //String sql="select o.type,o.url from interface o where o.id= ? ";
//        // Integer userId=1;
//        //Page<Map> orderLists = billRepository.findPageByParams(sql,new PageRequest(1,10),userId);
//        List<Bill> result = this.billRepository.findAll(new Sort(new Sort.Order(Sort.Direction.DESC,"id")));
//        return result;
//    }

}
