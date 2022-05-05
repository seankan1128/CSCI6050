package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.DTO.StatusNDescription;
import com.csci6050.ebooking.DTO.login_UP;
import com.csci6050.ebooking.entity.User;
import com.csci6050.ebooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller // This means that this class is a Controller
public class userController {

    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    @RequestMapping("userform")
    public Map<String, Object> sendinguserinfo() {

        Map<String,Object> returnMap = new HashMap<>();
        StatusNDescription SD = new StatusNDescription();

        Iterable<User> ulist = userRepository.findAll();
        List<login_UP> userlist = new ArrayList<>();
        for(User user : ulist){
            if(user.getUserType() == 1) {
                login_UP userinfo = new login_UP();
                userinfo.setEmail(user.getEmail());
                userinfo.setUserStatus(user.getStatus());
                userinfo.setFirstname(user.getFirstName());
                userlist.add(userinfo);
            }
        }

        SD.setDescription("Return success");
        SD.setStatus(1);

        returnMap.put("Userlist", userlist);
        returnMap.put("ReturnStatus", SD);

        return returnMap;
    }

    @ResponseBody
    @RequestMapping("userform2")
    public Map<String, Object> suspendUser(@RequestParam("userEmail") String email){
        Map<String,Object> returnMap = new HashMap<>();
        StatusNDescription SD = new StatusNDescription();

        User user = userRepository.findByEmail(email);
        user.setStatus(3);
        userRepository.save(user);

        SD.setDescription("Return success");
        SD.setStatus(1);
        returnMap.put("ReturnStatus", SD);

        return returnMap;
    }
}