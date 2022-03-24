package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.encrypt.passwordEncrypt;
import com.csci6050.ebooking.entity.User;
import com.csci6050.ebooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // This means that this class is a Controller
public class registrationController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registerPage(){

        return "register";
    }

    @ResponseBody
    @RequestMapping("/register2")
    public String addNewUser (User user) {
        passwordEncrypt pe = new passwordEncrypt();
        String encrypt = pe.encrypt(user.getPassword());

        User n = new User();
        n.setEmail(user.getEmail());
        n.setPassword(encrypt);
        n.setUserType(2);
        n.setStatus(2);
        userRepository.save(n);

        return "login";
    }
}