package com.updatatech.jasperreportDemo.controler;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(){
        return "index";
    }


    @GetMapping("/one")
    public String showOnePage(){
        return "one";
    }
}
