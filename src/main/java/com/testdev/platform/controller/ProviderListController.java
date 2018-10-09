package com.testdev.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ProviderListController {

    @RequestMapping("/providerAdd")
    public String providerAdd(){
        return "providerAdd";
    }

    @RequestMapping("/providerView")
    public String providerView(){
        return "providerView";
    }

    @RequestMapping("/providerUpdate")
    public String providerUpdate(){
        return "providerUpdate";
    }

}
