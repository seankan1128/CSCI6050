package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.DTO.login_UPSD;
import com.csci6050.ebooking.encrypt.passwordEncrypt;
import com.csci6050.ebooking.entity.Paymentcard;
import com.csci6050.ebooking.entity.User;
import com.csci6050.ebooking.repository.PaymentcardRepository;
import com.csci6050.ebooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public login_UPSD returnJson(User data) throws IOException {
//        Map<String, Object> returnMap = new HashMap<>(4);
        User n = userRepository.findByEmail(data.getEmail());
        login_UPSD upsd = new login_UPSD();
        String description = "";

        if(n == null){
            System.out.println("Email not found");
            description = "You do not have an account.";
//            returnMap.put("Status", 0);
//            returnMap.put("Description", description);
            upsd.setStatus(0);
            upsd.setDescription(description);
            return upsd;
        } else {
            passwordEncrypt en = new passwordEncrypt();
            String encrypt = en.encrypt(data.getPassword());

            if(!(encrypt.equals(n.getPassword()))){
                System.out.println("Password is wrong");
                description = "Your password is incorrect.";
//                returnMap.put("Status", 0);
//                returnMap.put("Description", description);
                upsd.setStatus(0);
                upsd.setDescription(description);
                return upsd;
            }

            data.setFirstName(n.getFirstName());
            data.setLastName(n.getLastName());
            data.setPassword("");
            data.setUserType(n.getUserType());
            data.setEmail(n.getEmail());
            data.setPhone(n.getPhone());
            data.setStatus(n.getStatus());
            data.setEnrolledForPromotions(n.getEnrolledForPromotions());
//            returnMap.put("User", data);
            upsd.setUser(data);

            Iterable<Paymentcard> pList = paymentcardRepository.findAllByUser(n);
            List<Paymentcard> paymentcardlist = new ArrayList<>();
            pList.forEach(paymentcardlist::add);
            upsd.setPaymentCardList(paymentcardlist);

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
            upsd.setStatus(status);
            upsd.setDescription(description);

        }
        return upsd;
    }
}