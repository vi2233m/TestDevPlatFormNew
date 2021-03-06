package com.testdev.platform.controller;

import com.testdev.platform.domain.Bill;
import com.testdev.platform.dao.BillRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class IndexController {

    @Autowired
    private BillRepositroy billRepository;
    @RequestMapping(value = "/billList")
    public String billList1(Model model, @RequestParam(value = "pageNo") String pageNum) {
        System.out.println("pageNo: " + pageNum);
        long count = this.billRepository.count();
        int pageSize = 10;
        long pageCount;
        if (count % pageSize != 0 ){
            pageCount = count/pageSize + 1;
        }else {
            pageCount = count/pageSize;
        }

        int pageNo = 1;
        if(pageNum != null && pageNum != ""){
            try {
                pageNo = Integer.parseInt(pageNum);
            }catch (Exception e){
                pageNo = 1;
            }

        }else {
            pageNo = 1;
        }

        if (pageNo > 1 && pageNo < pageCount) {
            Page<Bill> result1 = this.billRepository.findAll(new PageRequest(pageNo - 1, pageSize));
            model.addAttribute("interfaces", result1);
        }else if(pageNo >= pageCount){
            pageNo = (int)pageCount;
            Page<Bill> result1 = this.billRepository.findAll(new PageRequest(pageNo - 1, pageSize));
            model.addAttribute("interfaces", result1);
        }else {
            pageNo = 1;
            Page<Bill> result1 = this.billRepository.findAll(new PageRequest(pageNo - 1, pageSize));
            model.addAttribute("interfaces", result1);
        }

        model.addAttribute("count", count); //总条数
        model.addAttribute("pageCount", pageCount); //总页数
        model.addAttribute("pageNo", pageNo);//当前页数
        return "tables";
    }


}
