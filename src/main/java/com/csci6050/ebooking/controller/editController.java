package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.DTO.StatusNDescription;
import com.csci6050.ebooking.encrypt.passwordEncrypt;
import com.csci6050.ebooking.entity.Paymentcard;
import com.csci6050.ebooking.entity.User;
import com.csci6050.ebooking.repository.PaymentcardRepository;
import com.csci6050.ebooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller // This means that this class is a Controller
public class editController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentcardRepository paymentcardRepository;

    @RequestMapping(value = "/editprofile", method = RequestMethod.GET)
    public String editProfilePage(){
        return "edit_profile";
    }

    @ResponseBody
    @RequestMapping("userinfo")
    public Map<String, Object> edituserinfo(User user){
        Map<String, Object> returnMap = new HashMap<>();
        StatusNDescription SD = new StatusNDescription();
        SD.setStatus(1);
        SD.setDescription("Account information changed");
        User n = userRepository.findByEmail(user.getEmail());
        n.setFirstName(user.getFirstName());
        n.setLastName(user.getLastName());
        n.setEnrolledForPromotions(user.getEnrolledForPromotions());
        userRepository.save(n);
        return returnMap;
    }

    @ResponseBody
    @RequestMapping("addcard")
    public Map<String, Object> addpaymentcard(@RequestParam("email") String email, Paymentcard paymentcard){
        Map<String, Object> returnMap = new HashMap<>();
        StatusNDescription SD = new StatusNDescription();
        SD.setStatus(1);
        SD.setDescription("Payment card added");
        User n = userRepository.findByEmail(email);
        Paymentcard p = new Paymentcard();

        p.setUser(n);
        p.setBillingzipcode(paymentcard.getSecuritycode());
        p.setBillingstate(paymentcard.getBillingstate());
        p.setBillingcity(paymentcard.getBillingcity());
        p.setBillingaddress(paymentcard.getBillingaddress());

        passwordEncrypt en = new passwordEncrypt();
        p.setCardno(en.encrypt(paymentcard.getCardno()));
        p.setLastfourdigits(paymentcard.getCardno().substring(paymentcard.getCardno().length()-4));
        if (paymentcard.getCardno().charAt(0) == '4'){
            p.setType("Visa");
        } else if (paymentcard.getCardno().charAt(0) == '5') {
            p.setType("Master");
        } else if (paymentcard.getCardno().charAt(0) == '3') {
            p.setType("AE");
        } else if (paymentcard.getCardno().charAt(0) == '6') {
            p.setType("Discover");
        } else {
            p.setType("Other");
        }

        userRepository.save(n);
        return returnMap;
    }

}
