package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.DTO.StatusNDescription;
import com.csci6050.ebooking.encrypt.passwordEncrypt;
import com.csci6050.ebooking.entity.User;
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
public class path2changepwController {
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
    @RequestMapping("forgetpw")
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

    @GetMapping("forgetpw/verify")
    public String verifyUser(@Param("pwresetcode") String code) {
        System.out.println("Verifying");
        if (verify(code)) {
            return "reset_password";
        } else {
            return "wrong_code";
        }
    }

    @RequestMapping(value = "/old_password", method = RequestMethod.GET)
    public String registerFinishPage(){
        return "old_password";
    }

    @ResponseBody
    @RequestMapping("changepw")
    public Map<String, Object> go2changepw(@RequestParam("oldpassword") String oldpassword,@RequestParam("email") String email) {
        Map<String, Object> returnMap = new HashMap<>();
        StatusNDescription SD = new StatusNDescription();
        passwordEncrypt en = new passwordEncrypt();
        String encryptpw = en.encrypt(oldpassword);
        User n = userRepository.findByEmail(email);
        if(n.getPassword().equals(encryptpw)){
            SD.setStatus(1);
            SD.setDescription("correct password");
            returnMap.put("ReturnStatus",SD);
            String pwresetcode = RandomString.make(64);
            n.setPwresetcode(pwresetcode);
            userRepository.save(n);
            returnMap.put("code", pwresetcode);
            return returnMap;
        }
        SD.setStatus(0);
        SD.setDescription("wrong password");
        returnMap.put("ReturnStatus",SD);
        return returnMap;
    }

    @GetMapping(value = "send_password")
    public String sendpwpage(){
        return "send_password";
    }

}

