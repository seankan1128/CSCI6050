package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.encrypt.passwordDecrypt;
import com.csci6050.ebooking.encrypt.passwordEncrypt;
import com.csci6050.ebooking.entity.User;
import com.csci6050.ebooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.csci6050.ebooking.encrypt.passwordDecrypt.decrypt;

@Controller
public class loginController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/LogIn", method = RequestMethod.GET)
    public String loginPage(){

        return "login";
    }

    @ResponseBody
    @RequestMapping("/login2")
    public String submitLogin (User user) {
        User n = userRepository.findByEmail(user.getEmail());

        passwordDecrypt de = new passwordDecrypt();
        String decrypt = decrypt(user.getPassword());

        System.out.print(decrypt);

        if(n == null){
            System.out.println("Email not found");
        }
        else{
            if(!(decrypt.equals(user.getPassword()))){
                System.out.println("Password is wrong");
            }
            else{
                if(n.getStatus() == 1){
                    if(n.getUserType() == 1) {
                        System.out.println("You are an admin");
                    }
                   else if(n.getUserType() == 2) {
                        System.out.println("You are a customer");
                    }
                    else if(n.getUserType() == 3) {
                        System.out.println("You are an employee");
                    }
                }
                if(n.getStatus() == 2){
                    System.out.println("Your account is inactive");
                }
                if(n.getStatus() == 3){
                    System.out.println("Your account is suspended");
                }
            }
        }

        return "login";
    }
}