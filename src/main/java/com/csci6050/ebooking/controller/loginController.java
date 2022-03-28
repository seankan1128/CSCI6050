package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.encrypt.passwordDecrypt;
import com.csci6050.ebooking.entity.Paymentcard;
import com.csci6050.ebooking.entity.User;
import com.csci6050.ebooking.repository.PaymentcardRepository;
import com.csci6050.ebooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class loginController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentcardRepository paymentcardRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(){

        return "login";
    }

    @ResponseBody
    @RequestMapping("/login2")
    public Map<String, Object> returnJson(User data) throws IOException {
        Map<String, Object> returnMap = new HashMap<>(4);
        User n = userRepository.findByEmail(data.getEmail());
        String description = "";

        if(n == null){
            System.out.println("Email not found");
            description = "You do not have an account.";
            returnMap.put("Status", 0);
            returnMap.put("Description", description);
            return returnMap;
        } else {
            passwordDecrypt de = new passwordDecrypt();
            String decrypt = de.decrypt(n.getPassword()); //correct pw from db

            if(!(decrypt.equals(data.getPassword()))){
                System.out.println("Password is wrong");
                description = "Your password is incorrect.";
                returnMap.put("Status", 0);
                returnMap.put("Description", description);
                return returnMap;
            }

            data.setFirstName(n.getFirstName());
            data.setLastName(n.getLastName());
            data.setEmail(n.getEmail());
            data.setPhone(n.getPhone());
            data.setStatus(n.getStatus());
            data.setEnrolledForPromotions(n.getEnrolledForPromotions());
            returnMap.put("User", data);

            Paymentcard p = paymentcardRepository.findByUser(n);
            Paymentcard temp = new Paymentcard();
            temp.setCardno(de.decrypt(p.getCardno()));
            returnMap.put("PaymentCard", temp);

//            for(int i = 0; i < 3; i++){
//
//            }

            int status = 0;

            if(n.getStatus() == 1){
                if(n.getUserType() == 1) {
                    System.out.println("You are an admin");
                    status = 1;
                    description = "You are an admin";
                }
                else if(n.getUserType() == 2) {
                    System.out.println("You are a customer");
                    status = 1;
                    description = "You are a customer";
                }
                else if(n.getUserType() == 3) {
                    System.out.println("You are an employee");
                    status = 1;
                    description = "You are an employee";
                }
            }
            if(n.getStatus() == 2){
                System.out.println("Your account is inactive");
                description = "Your account is inactive";
            }
            if(n.getStatus() == 3){
                System.out.println("Your account is suspended");
                description = "Your account is suspended. Please contact an administrator.";
            }
            returnMap.put("Status", status);
            returnMap.put("Description", description);
        }
        return returnMap;
    }
}