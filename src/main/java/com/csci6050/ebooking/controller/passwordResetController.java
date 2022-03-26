package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.encrypt.passwordEncrypt;
import com.csci6050.ebooking.entity.User;
import com.csci6050.ebooking.repository.PaymentcardRepository;
import com.csci6050.ebooking.repository.UserRepository;
import com.csci6050.ebooking.tool.Email;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // This means that this class is a Controller
public class passwordResetController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/forget_password")
    public String forgetPassword(){
        return "forget_password";
    }

    @ResponseBody
    public void reset(@RequestParam String email) {
        User n = userRepository.findByEmail(email);

        if(n == null){
            System.out.println("Account doesn't exists");
        }
        else{
            String randompw = RandomString.make(16);
            passwordEncrypt pe = new passwordEncrypt();
            String encrypt = pe.encrypt(randompw);

            n.setPassword(encrypt);
            userRepository.save(n);

            Email em = new Email();
            em.resetPassword(n, randompw);
        }


    }

}

