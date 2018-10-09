package com.testdev.platform.controller;

import com.testdev.platform.domain.Bill;
import com.testdev.platform.services.BillRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {

    @Autowired
    private BillRepositroy billRepository;
    @RequestMapping("/tables")
    public String billList(Model model) {

        // List<Bill> result = this.billRepository.findAll(new Sort(new Sort.Order(Sort.Direction.DESC,"updated")));
        Page<Bill> result1 = this.billRepository.findAll(new PageRequest(0, 10));
        model.addAttribute("interfaces", result1);
        return "tables";
    }

    @RequestMapping("/providerList")
    public String providerList(){
        return "providerList";
    }

    @RequestMapping("/userList")
    public String userList(){
        return "/users/userList";
    }

    @RequestMapping("/password")
    public String password(){
        return "password";
    }

//    @RequestMapping("/tables")
//    public String tables(){
//        return "tables";
//    }

}
