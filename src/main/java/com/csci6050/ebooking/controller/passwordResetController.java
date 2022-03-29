package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.DTO.StatusNDescription;
import com.csci6050.ebooking.encrypt.passwordEncrypt;
import com.csci6050.ebooking.entity.User;
import com.csci6050.ebooking.repository.PaymentcardRepository;
import com.csci6050.ebooking.repository.UserRepository;
import com.csci6050.ebooking.tool.Email;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller // This means that this class is a Controller
public class passwordResetController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/forget_password")
    public String forgetPassword(){
        return "forget_password";
    }

    public boolean verify(String pwresetcode) {
        User user = userRepository.findByPwresetcode(pwresetcode);
        if (user == null) {
            return false;
        } else {
            return true;
        }
    }

    @ResponseBody
    @RequestMapping("resetpassword")
    public Map<String, Object> reset(@RequestParam String email, HttpServletRequest request) {

        Map<String, Object> returnMap = new HashMap<>();
        StatusNDescription SD = new StatusNDescription();
        User n = userRepository.findByEmail(email);

        if(n == null){
            System.out.println("Cant find the user in DB");
            SD.setDescription("Account does not exist");
            SD.setStatus(0);
            returnMap.put("ReturnStatus", SD);
            return returnMap;
        }
        else{
            System.out.println("found user in db");
            String pwresetcode = RandomString.make(64);

            n.setPwresetcode(pwresetcode);
            userRepository.save(n);

            Email resetEmail = new Email();
            resetEmail.resetPassword(n, request.getRequestURL().toString());

            SD.setDescription("Email has been sent");
            SD.setStatus(1);
            returnMap.put("ReturnStatus", SD);
            return returnMap;
        }
    }

    @GetMapping("resetpassword/verify")
    public String verifyUser(@Param("pwresetcode") String code) {
        System.out.println("Verifying");
        if (verify(code)) {
            return "reset_password";
        } else {
            return "wrong_code";
        }
    }



}

