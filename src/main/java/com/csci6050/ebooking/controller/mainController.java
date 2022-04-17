package com.csci6050.ebooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
public class mainController {
    @GetMapping("/")
    public String mainpage(){
        return "index";
    }

    // @ResponseBody
    // @RequestMapping("")

}
