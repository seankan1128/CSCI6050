package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.encrypt.passwordEncrypt;
import com.csci6050.ebooking.entity.Paymentcard;
import com.csci6050.ebooking.entity.User;
import com.csci6050.ebooking.repository.PaymentcardRepository;
import com.csci6050.ebooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // This means that this class is a Controller
public class registrationController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentcardRepository paymentcardRepository;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registerPage(){

        return "register2";
    }

    @ResponseBody
    @RequestMapping("registerform")
    public String addNewUser (User user, Paymentcard paymentcard) {
        passwordEncrypt pe = new passwordEncrypt();
        String encrypt = pe.encrypt(user.getPassword());

        User n = new User();
        n.setFirstName(user.getFirstName());
        n.setLastName(user.getLastName());
        n.setEmail(user.getEmail());
        n.setPassword(encrypt);
        n.setPhone(user.getPhone());
        n.setStatus(2);
        n.setEnrolledForPromotions(user.getEnrolledForPromotions());
        n.setUserType(2);
        userRepository.save(n);

        Paymentcard p = new Paymentcard();
        p.setCardno(paymentcard.getCardno());
        p.setExpirationdate(paymentcard.getExpirationdate());
        if (p.getCardno().substring(0, 1).equals("4")){
            p.setType("Visa");
        } else if (p.getCardno().substring(0, 1).equals("5")) {
            p.setType("Master");
        } else if (p.getCardno().substring(0, 1).equals("3")) {
            p.setType("AE");
        } else if (p.getCardno().substring(0, 1).equals("6")) {
            p.setType("Discover");
        } else {
            p.setType("Other");
        }
        p.setUser(n);
        p.setBillingaddress(paymentcard.getBillingaddress());

        paymentcardRepository.save(p);

        return "register_finish";
    }
}