package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.bean.users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class loginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String registerPage(){

        return "login";
    }

    @ResponseBody
    @RequestMapping("/login2")
    public String test(users message) throws ServletException, IOException {
        String username = message.getEmail();
        String password = message.getPassword();
        System.out.println(username);
        System.out.println(password);
        return "yes";
    }
}
