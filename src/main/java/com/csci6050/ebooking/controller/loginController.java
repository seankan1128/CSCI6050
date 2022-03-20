package com.csci6050.ebooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class loginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String registerPage(){
        return "login";
    }


}
