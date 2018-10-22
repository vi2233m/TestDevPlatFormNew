package com.testdev.platform.controller;

import com.testdev.platform.dao.BillDao;
import com.testdev.platform.domain.Bill;
import com.testdev.platform.dao.BillRepositroy;
import com.testdev.platform.services.BillSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashMap;
import java.util.List;

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
    private BillDao billDao;
    @RequestMapping("/interface/{id}")
    public String billView(@PathVariable ("id") int id){
        System.out.println("id: " + id);
        return "formelements";
    }

    @Autowired
    private BillSearch billSearch;
    @RequestMapping(value = "/tables1")
    public String billSearch(@RequestParam("pageNo") int pageNo, @RequestParam(value ="name") String name, @RequestParam("type") String type, @RequestParam("url") String url, @RequestParam("header") String header, Model model) {
       System.out.println("=============================================================================");
        System.out.println("name: " + name + " type: " + type + " url: " + url + " header: " + header);

//        Bill result = this.billDao.searchBillByEm(name, type, url, header, 0, 10);
//        model.addAttribute("results", result);
//        return "tables1";
        LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        if (name != null && name !=""){
            map.put("name",name);
        }
        if (type != null && type != ""){
            map.put("type", type);
        }
        if (url != null && url != ""){
            map.put("url", url);
        }
        if (header != null && header != ""){
            map.put("header", header);
        }

        int pageSize = 10;
        long count;
        long pageCount;

        if (map.isEmpty()){
            Page<Bill> results = this.billRepository.findAll(new PageRequest(pageNo-1, pageSize));

            count = this.billRepository.count();
            if (count % pageSize != 0 ){
                pageCount = count/pageSize + 1;
            }else {
                pageCount = count/pageSize;
            }
            model.addAttribute("interfaces", results);

        }else {

            count = (long)this.billSearch.findCounts(map);
            if (count % pageSize != 0 ){
                pageCount = count/pageSize + 1;
            }else {
                pageCount = count/pageSize;
            }
            List<Bill> results = billSearch.findBillPage(map, pageNo-1, pageSize);
            model.addAttribute("interfaces", results);
        }

        model.addAttribute("count", count); //总条数
        model.addAttribute("pageCount", pageCount); //总页数
        model.addAttribute("pageNo", pageNo);//当前页数

        return "tables2";
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
