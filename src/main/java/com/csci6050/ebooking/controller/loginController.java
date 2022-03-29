package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.DTO.StatusNDescription;
import com.csci6050.ebooking.DTO.login_UP;
import com.csci6050.ebooking.encrypt.passwordEncrypt;
import com.csci6050.ebooking.entity.Paymentcard;
import com.csci6050.ebooking.entity.User;
import com.csci6050.ebooking.repository.PaymentcardRepository;
import com.csci6050.ebooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.sql.Statement;
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
    public Map<String, Object> returnJson(User data) throws IOException {
        Map<String, Object> returnMap = new HashMap<>();
        User n = userRepository.findByEmail(data.getEmail());
        StatusNDescription SD = new StatusNDescription();
        String description = "";

        if(n == null){
            System.out.println("Email not found");
            description = "You do not have an account.";
//            returnMap.put("Status", 0);
//            returnMap.put("Description", description);
            SD.setStatus(0);
            SD.setDescription(description);
            returnMap.put("ReturnStatus", SD);
            return returnMap;
        } else {
            passwordEncrypt en = new passwordEncrypt();
            String encrypt = en.encrypt(data.getPassword());

            if(!(encrypt.equals(n.getPassword()))){
                System.out.println("Password is wrong");
                description = "Your password is incorrect.";
//                returnMap.put("Status", 0);
//                returnMap.put("Description", description);
                SD.setStatus(0);
                SD.setDescription(description);
                returnMap.put("ReturnStatus", SD);
                return returnMap;
            }

            if(n.getStatus() == 2){
                System.out.println("Your account is inactive");
                description = "Your account is inactive";
                SD.setStatus(0);
                SD.setDescription(description);
                returnMap.put("ReturnStatus", SD);
                return returnMap;
            }
            if(n.getStatus() == 3){
                System.out.println("Your account is suspended");
                description = "Your account is suspended. Please contact an administrator.";
                SD.setStatus(0);
                SD.setDescription(description);
                returnMap.put("ReturnStatus", SD);
                return returnMap;
            }

            login_UP up = new login_UP();

            up.setFirstname(n.getFirstName());
            up.setLastname(n.getLastName());
            up.setUserType(n.getUserType());
            up.setEmail(n.getEmail());
            up.setPhone(n.getPhone());
            up.setUserType(n.getUserType());
            up.setEnrolledForPromotions(n.getEnrolledForPromotions());

            Iterable<Paymentcard> pList = paymentcardRepository.findAllByUser(n);
            List<Paymentcard> paymentcardlist = new ArrayList<>();
            pList.forEach(paymentcardlist::add);
            up.setPaymentCardList(paymentcardlist);

            returnMap.put("ReturnUser", up);


            int status;

            if(n.getStatus() == 1){
                if(n.getUserType() == 1) {
                    System.out.println("You are an admin");
                    status = 1;
                    description = "You are an admin";
                    SD.setStatus(status);
                    SD.setDescription(description);
                }
                else if(n.getUserType() == 2) {
                    System.out.println("You are a customer");
                    status = 1;
                    description = "You are a customer";
                    SD.setStatus(status);
                    SD.setDescription(description);
                }
                else if(n.getUserType() == 3) {
                    System.out.println("You are an employee");
                    status = 1;
                    description = "You are an employee";
                    SD.setStatus(status);
                    SD.setDescription(description);
                }
            }

            returnMap.put("ReturnStatus", SD);


        }
        return returnMap;
    }
}