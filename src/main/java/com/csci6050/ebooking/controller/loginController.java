package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.encrypt.passwordDecrypt;
import com.csci6050.ebooking.encrypt.passwordEncrypt;
import com.csci6050.ebooking.entity.User;
import com.csci6050.ebooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.csci6050.ebooking.encrypt.passwordDecrypt.decrypt;

@Controller
public class loginController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(){

        return "login";
    }


//    @ResponseBody
//    @RequestMapping("/login2")
//    @GetMapping
//    public String submitLogin (User user,Model model, HttpServletResponse response) throws IOException {
//        User n = userRepository.findByEmail(user.getEmail());
//
////        System.out.print(decrypt);
//
//        if(n == null){
//            System.out.println("Email not found");
//        }
//        else{
//            passwordDecrypt de = new passwordDecrypt();
//            String decrypt = de.decrypt(n.getPassword());
//
//            if(!(decrypt.equals(user.getPassword()))){
//                System.out.println("Password is wrong");
//            }
//            else{
//                model.addAttribute("User", n);
//                if(n.getStatus() == 1){
//                    if(n.getUserType() == 1) {
//                        System.out.println("You are an admin");
//                    }
//                    else if(n.getUserType() == 2) {
//                        System.out.println("You are a customer");
//                    }
//                    else if(n.getUserType() == 3) {
//                        System.out.println("You are an employee");
//                    }
//                }
//                if(n.getStatus() == 2){
//                    System.out.println("Your account is inactive");
//                    return "login_inactive";
//                }
//                if(n.getStatus() == 3){
//                    System.out.println("Your account is suspended");
//                }
//            }
//        }
//
//        return "login";
//    }
    @ResponseBody
    @RequestMapping("/login2")
//    @PostMapping("/login2")
    public Map<String, Object> test(User data) {
        Map<String, Object> returnMap = new HashMap<>(3);
        List loginStatus = new ArrayList<>();
        loginStatus.add(1);
        loginStatus.add("Success");

        returnMap.put("User", data);
        returnMap.put("Status", loginStatus);
//        returnMap.put()

//        User n = userRepository.findByEmail(data.getEmail());
//        data.setFirstName(n.getFirstName());
//        data.setLastName(n.getLastName());
//        data.setEmail(n.getEmail());
//        data.setPassword(n.getPassword());
//        data.setPhone(n.getPhone());
//        data.setStatus(n.getStatus());
//        data.setEnrolledForPromotions(n.getEnrolledForPromotions());

        return returnMap;
    }

//    @ResponseBody
//    @RequestMapping("/login2")
//    public Object submitLogin (User user, HttpServletResponse response) throws IOException {
//        User n = userRepository.findByEmail(user.getEmail());
//
//        passwordDecrypt de = new passwordDecrypt();
//        String decrypt = de.decrypt(n.getPassword());
//
//        System.out.print(decrypt);
//
//        if(n == null){
//            System.out.println("Email not found");
//        }
//        else{
//            if(!(decrypt.equals(user.getPassword()))){
//                System.out.println("Password is wrong");
//            }
//            else{
//                if(n.getStatus() == 1){
//                    if(n.getUserType() == 1) {
//                        System.out.println("You are an admin");
//                    }
//                    else if(n.getUserType() == 2) {
//                        System.out.println("You are a customer");
//                    }
//                    else if(n.getUserType() == 3) {
//                        System.out.println("You are an employee");
//                    }
//                }
//                if(n.getStatus() == 2){
//                    System.out.println("Your account is inactive");
//                    response.sendRedirect("login_inactive");
//                }
//                if(n.getStatus() == 3){
//                    System.out.println("Your account is suspended");
//                }
//            }
//        }
//
//        return n;
//    }
}