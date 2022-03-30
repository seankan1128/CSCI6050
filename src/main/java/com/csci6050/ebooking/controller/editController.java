package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.DTO.StatusNDescription;
import com.csci6050.ebooking.entity.User;
import com.csci6050.ebooking.repository.PaymentcardRepository;
import com.csci6050.ebooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller // This means that this class is a Controller
public class editController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentcardRepository paymentcardRepository;

    @RequestMapping(value = "/editprofile", method = RequestMethod.GET)
    public String editProfilePage(){
        return "edit_profile";
    }

    @ResponseBody
    @RequestMapping("userinfo")
    public Map<String, Object> edituserinfo(User user){
        Map<String, Object> returnMap = new HashMap<>();
        StatusNDescription SD = new StatusNDescription();
        SD.setStatus(1);
        SD.setDescription("Account information changed");
        User n = userRepository.findByEmail(user.getEmail());
        n.setFirstName(user.getFirstName());
        n.setLastName(user.getLastName());
        n.setEnrolledForPromotions(user.getEnrolledForPromotions());
        userRepository.save(n);
        return returnMap;
    }
}
