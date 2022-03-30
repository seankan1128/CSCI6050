package com.csci6050.ebooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // This means that this class is a Controller
public class mainController {
    @GetMapping("/")
    public String forgetPassword(){
        return "index";
    }
}
