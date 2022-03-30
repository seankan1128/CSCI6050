package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.encrypt.passwordDecrypt;
import com.csci6050.ebooking.entity.User;
import com.csci6050.ebooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
public class changepasswordController {

    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    @RequestMapping("resetpassword")
    public void changepw(@Param("pwresetcode") String code, @RequestParam("newpassword") String newpw){
        User n = userRepository.findByPwresetcode(code);
        passwordDecrypt de = new passwordDecrypt();
        n.setPassword(de.decrypt(newpw));
        userRepository.save(n);
    }
}
