package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.bean.users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

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
    public String hello(@RequestParam("userID") String name,
                        @RequestParam("password") String age) {
        System.out.print(name+""+age);
        return "name：" + name + "\nage：" + age;
    }

}
