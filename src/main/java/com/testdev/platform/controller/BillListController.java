package com.testdev.platform.controller;

import com.testdev.platform.dao.BillDao;
import com.testdev.platform.domain.Bill;
import com.testdev.platform.dao.BillRepositroy;
import com.testdev.platform.services.BillAdd;
import com.testdev.platform.services.BillDelete;
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

    @RequestMapping(value = "/billAddView")
    public String billAddView(){

        return "formelements";
    }

    //根据ID 查询出具体的对象信息
    @Autowired
    private BillRepositroy billRepository;
    @Autowired
    private BillAdd billAdd;
    @RequestMapping(value = "/billAdd")
    public String billAdd( @RequestParam("name") String name, @RequestParam("url") String url, @RequestParam("type") String type, @RequestParam("header") String header, @RequestParam("body") String body, Model model){
        System.out.println("=============================================================================");
        System.out.println("name: " + name + " type: " + type + " url: " + url + " header: " + header);

        Bill bill = new Bill();
        if(name != null && name != ""){
            bill.setName(name);
        }
        if(url != null && url != ""){
            bill.setUrl(url);
        }
        if(type != null && type != ""){
            bill.setType(type);
        }
        if(header != null && header != ""){
            bill.setHeader(header);
        }
        if(body != null && body != ""){
            bill.setBody(body);
        }

        boolean isTure = billAdd.billSave(bill);
        model.addAttribute("isTrue", isTure);

        return "formelements";
    }

//    @Autowired
//    private BillRepositroy billRepository;
    @Autowired
    private BillSearch billSearch;
    @RequestMapping(value = "/billSearch")
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
            // 限制最大页数和最小页数
            if(pageNo > pageCount){
                pageNo = (int) pageCount;
            }
            if(pageNo < 1){
                pageNo = 1;
            }
            List<Bill> results = billSearch.findBillPage(map, pageNo, pageSize);
            model.addAttribute("interfaces", results);
        }

        model.addAttribute("count", count); //总条数
        model.addAttribute("pageCount", pageCount); //总页数
        model.addAttribute("pageNo", pageNo);//当前页数
        model.addAttribute("map",map);

        return "tables2";
    }


    @RequestMapping("/billDelete")
    public String billDelete(@RequestParam("id") int id){

        Bill bill = new Bill();
        BillDelete billDelete = new BillDelete();
        if(id != 0 ){
//            bill.setId(id);
            Object o = billDelete.findById(bill, id);
            System.out.println("Bill: " + bill.getId() +"  " + bill.getUrl() +"  " + bill.getType() +"  " + bill.getHeader() +"  " + bill.getBody());
            billDelete.billDelete((Bill) o);
        }
        return "tables";
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
