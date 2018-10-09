package com.testdev.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class UserListController {

    @RequestMapping("/userAdd")
    public String userAdd(){
        return "/users/userAdd";
    }

    @RequestMapping("/userView")
    public String userView(){
        return "/users/userView";
    }

    @RequestMapping("/userUpdate")
    public String userUpdate(){
        return "/users/userUpdate";
    }
}
