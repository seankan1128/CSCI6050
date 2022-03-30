package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.DTO.StatusNDescription;
import com.csci6050.ebooking.encrypt.passwordDecrypt;
import com.csci6050.ebooking.encrypt.passwordEncrypt;
import com.csci6050.ebooking.entity.User;
import com.csci6050.ebooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller // This means that this class is a Controller
public class changepasswordController {

    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    @RequestMapping("forgetpw/verify2")
    public Map<String, Object> changepw(@RequestParam("code") String code, @RequestParam("newpassword") String newpw){
        System.out.println(code);
        System.out.println(newpw);
        User n = userRepository.findByPwresetcode(code);
        passwordEncrypt en = new passwordEncrypt();
        n.setPassword(en.encrypt(newpw));
        userRepository.save(n);
        System.out.println("password changed");
        StatusNDescription SD = new StatusNDescription();
        SD.setStatus(1);
        SD.setDescription("password changed");
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("ReturnStatus", SD);
        return returnMap;
    }


}
