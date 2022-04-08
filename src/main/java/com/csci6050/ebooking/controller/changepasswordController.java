package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.DTO.StatusNDescription;
import com.csci6050.ebooking.encrypt.passwordEncrypt;
import com.csci6050.ebooking.entity.User;
import com.csci6050.ebooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller // This means that this class is a Controller
public class changepasswordController {

    @Autowired
    private UserRepository userRepository; // For saving user entity in the database

    @ResponseBody
    @RequestMapping("forgetpw/verify2")
    public Map<String, Object> changepw(@RequestParam("code") String code, @RequestParam("newpassword") String newpw){
        System.out.println(code);
        System.out.println(newpw);
        User n = userRepository.findByPwresetcode(code);
        passwordEncrypt en = new passwordEncrypt();
        n.setPassword(en.encrypt(newpw));
        userRepository.save(n);
        System.out.println("password changed");
        StatusNDescription SD = new StatusNDescription();
        SD.setStatus(1);
        SD.setDescription("password changed");
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("ReturnStatus", SD);
        return returnMap;
    }

    @GetMapping(value = "forgetpw/change_password_finish")
    public String finishpage(){
        return "change_password_finish";
    }

}
