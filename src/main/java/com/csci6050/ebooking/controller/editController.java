package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.DTO.Login_Pay;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        User n = userRepository.findByEmail(email);
        passwordEncrypt en = new passwordEncrypt();

        Iterable<Paymentcard> pList = paymentcardRepository.findAllByUser(n);
        List<Login_Pay> paymentcardlist = new ArrayList<>();
//            pList.forEach(paymentcardlist::add);
        for(Paymentcard pay : pList){
            paymentcardlist.add(new Login_Pay(pay.getType(),pay.getExpirationdate(),pay.getBillingaddress(),pay.getLastfourdigits(),pay.getBillingcity(),pay.getBillingstate(),pay.getBillingzipcode()));
        }
        if(paymentcardlist.size() > 2 || paymentcardlist.contains(en.encrypt(paymentcard.getCardno()))){
            SD.setStatus(0);
            SD.setDescription("User cannot set more than 3 cards and cards need to be unique");
            returnMap.put("ReturnStatus", SD);
            return returnMap;
        }

        SD.setStatus(1);
        SD.setDescription("Payment card added");

        returnMap.put("ReturnStatus", SD);

        Paymentcard p = new Paymentcard();

        p.setUser(n);
        p.setBillingzipcode(paymentcard.getSecuritycode());
        p.setBillingstate(paymentcard.getBillingstate());
        p.setBillingcity(paymentcard.getBillingcity());
        p.setBillingaddress(paymentcard.getBillingaddress());
        p.setExpirationdate(paymentcard.getExpirationdate());

        p.setCardno(en.encrypt(paymentcard.getCardno()));
        p.setSecuritycode(en.encrypt(paymentcard.getSecuritycode()));

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

        paymentcardRepository.save(p);

        login_UP up = new login_UP();

        up.setFirstname(n.getFirstName());
        up.setLastname(n.getLastName());
        up.setUserType(n.getUserType());
        up.setEmail(n.getEmail());
        up.setPhone(n.getPhone());
        up.setUserType(n.getUserType());
        up.setEnrolledForPromotions(n.getEnrolledForPromotions());
        up.setBirthday(n.getBirthday());

        up.setPaymentCardList(paymentcardlist);

        returnMap.put("ReturnUser", up);
        return returnMap;
    }

}
