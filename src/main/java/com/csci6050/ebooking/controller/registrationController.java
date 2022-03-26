package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.encrypt.passwordEncrypt;
import com.csci6050.ebooking.entity.Paymentcard;
import com.csci6050.ebooking.entity.User;
import com.csci6050.ebooking.repository.PaymentcardRepository;
import com.csci6050.ebooking.repository.UserRepository;
import com.csci6050.ebooking.tool.Email;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

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

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @ResponseBody
    @RequestMapping("registerform")
    public void addNewUser (User user, Paymentcard paymentcard, HttpServletRequest request, HttpServletResponse response) throws MessagingException, IOException {
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

        String randomCode = RandomString.make(64);
        n.setVerificationCode(randomCode);

        userRepository.save(n);

        Email verifyEmail = new Email();
        verifyEmail.verificationEmail(n, request.getRequestURL().toString());

        Paymentcard p = new Paymentcard();

        String cardencrypt = pe.encrypt(paymentcard.getCardno());
        p.setCardno(cardencrypt);

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

//        response.sendRedirect("register_finish");
    }

    public boolean verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);

        if (user == null || user.getStatus() == 1) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setStatus(1);
            userRepository.save(user);

            return true;
        }
    }
    @GetMapping("registerform/verify")
    public String verifyUser(@Param("verificationcode") String code) {
        if (verify(code)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }
}