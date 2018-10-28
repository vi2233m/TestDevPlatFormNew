package com.testdev.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        //当浏览器访问 url 根目录 “/” 时，会返回 /templates/login.html页面
            return "login";
    }


    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }


    @Autowired HttpServletRequest  request;

    @RequestMapping("/index")
    public String hello(HttpServletResponse response){

//        response.setHeader("ddd","123");
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        if(username.equals("test") && password.equals("123") ){
//            return "index";
//        }else {
//            return "login";
//        }
        return "index";
    }

}
