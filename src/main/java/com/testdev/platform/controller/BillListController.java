package com.testdev.platform.controller;

import com.testdev.platform.domain.Bill;
import com.testdev.platform.dao.BillRepositroy;
import com.testdev.platform.services.BillAdd;
import com.testdev.platform.services.BillSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class BillListController {

    //根据ID 查询出具体的对象信息
    @Autowired
    private BillRepositroy billRepository;
    @Autowired
    private BillAdd billAdd;
    @RequestMapping(value = "/billAdd")
    public String billAdd( @RequestParam("name") String name,
                           @RequestParam("url") String url,
                           @RequestParam("type") String type,
                           @RequestParam("header") String header,
                           @RequestParam("body") String body, Model model, RedirectAttributes attributes){
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
        int id = bill.getId();
        attributes.addAttribute("addStatus", isTure);
        attributes.addAttribute("id", id);

        return "redirect:/billView";
    }

    @RequestMapping(value = "/billAddView")
    public String billAddView(){
        return "add";
    }


    @Autowired
    private BillSearch billSearch;
    @RequestMapping(value = "/billSearch")
    public String billSearch(@RequestParam("pageNo") String pageNum,
                             @RequestParam("name") String name,
                             @RequestParam("type") String type,
                             @RequestParam("url") String url,
                             @RequestParam("header") String header, Model model) {
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
        int pageNo;

        try {
           pageNo = Integer.parseInt(pageNum);
        }catch (Exception e){
            pageNo = 1;
        }

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

    /**
     * 删除一条记录
     * @param id
     * @param attributes
     * @return
     */
    @RequestMapping("/billDelete")
    public String billDelete(@RequestParam("id") int id, RedirectAttributes attributes){

        if(id != 0 ){
            billRepository.deleteById(id);
            attributes.addFlashAttribute("success", "删除成功！！！");
        }
        int pageNo = 1;
        attributes.addAttribute("pageNo", pageNo);

        return "redirect:/billList";
    }


    @RequestMapping(value = "/billView")
    public String billView(@RequestParam("id") int id, @RequestParam("addStatus") boolean addStatus, Model model){

        boolean isAddTrue;
        Bill bill = billRepository.getOne(id);
        model.addAttribute("bill", bill);
        isAddTrue = addStatus;
        if (isAddTrue){
            model.addAttribute("isAddTrue", isAddTrue);
        }

        return "view";
    }

    @RequestMapping(value = "/billUpadate")
    public String billUpadate(@RequestParam("id") int id,
                              @RequestParam("name") String name,
                              @RequestParam("type") String type,
                              @RequestParam("url") String url,
                              @RequestParam("header") String header,
                              @RequestParam("body") String body, Model model){

        Bill bill = new Bill();
        if(id != 0){
            bill.setId(id);
        }
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
        billSearch.updateBill(bill);
        model.addAttribute("isTrue", true);
        model.addAttribute(bill);

        return "view";
    }

}
