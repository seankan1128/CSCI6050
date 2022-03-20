package com.csci6050.ebooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class loginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String registerPage(){

        return "login";
    }

    @ResponseBody
    @RequestMapping("/login2")
    public String test(String message) {
        System.out.println(message);
        return "yes";
    }
}
