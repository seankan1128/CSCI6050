package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.DTO.Login_Pay;
import com.csci6050.ebooking.DTO.StatusNDescription;
import com.csci6050.ebooking.DTO.login_UP;
import com.csci6050.ebooking.encrypt.passwordEncrypt;
import com.csci6050.ebooking.entity.Paymentcard;
import com.csci6050.ebooking.entity.User;
import com.csci6050.ebooking.repository.PaymentcardRepository;
import com.csci6050.ebooking.repository.UserRepository;
import com.csci6050.ebooking.tool.Email;
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
        returnMap.put("ReturnStatus",SD);
        User n = userRepository.findByEmail(user.getEmail());
        n.setFirstName(user.getFirstName());
        n.setLastName(user.getLastName());
        n.setEnrolledForPromotions(user.getEnrolledForPromotions());
        userRepository.save(n);
        Email email = new Email();
        email.editProfileNotification(n);
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
        List<Paymentcard> paymentcardlist = new ArrayList<>();
            pList.forEach(paymentcardlist::add);
        if(paymentcardlist.size() > 2 ){
            SD.setStatus(0);
            SD.setDescription("User cannot set more than 3 cards");
            returnMap.put("ReturnStatus", SD);
            return returnMap;
        }
        if(isduplicate(paymentcardlist,paymentcard)){
            SD.setStatus(0);
            SD.setDescription("User cannot have duplicate card");
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

        Iterable<Paymentcard> pList2 = paymentcardRepository.findAllByUser(n);
        List<Login_Pay> paymentcardlist2 = new ArrayList<>();
//            pList.forEach(paymentcardlist2::add);
        for(Paymentcard pay : pList2){
            paymentcardlist2.add(new Login_Pay(pay.getType(),pay.getExpirationdate(),pay.getBillingaddress(),pay.getLastfourdigits(),pay.getBillingcity(),pay.getBillingstate(),pay.getBillingzipcode()));
        }

        returnMap.put("paymentCardList", paymentcardlist2);
        Email email2 = new Email();
        email2.editProfileNotification(n);
        return returnMap;
    }

    private boolean isduplicate(List<Paymentcard> paymentcardList, Paymentcard paymentcard){
        passwordEncrypt en = new passwordEncrypt();
        for (Paymentcard p : paymentcardList){
            if(p.getCardno().equals(en.encrypt(paymentcard.getCardno()))){
                return true;
            }
        }
        return false;
    }

    @ResponseBody
    @RequestMapping("deletecard")
    public Map<String, Object> deletepaymentcard(@RequestParam("email") String email, @RequestParam("lastfourdigits") String lastfourdigits){
        Map<String, Object> returnMap = new HashMap<>();
        StatusNDescription SD = new StatusNDescription();
        User n = userRepository.findByEmail(email);
        Iterable<Paymentcard> pList = paymentcardRepository.findAllByUser(n);
        List<Paymentcard> paymentcardlist = new ArrayList<>();
        pList.forEach(paymentcardlist::add);
        if(paymentcardlist.size() < 2 ){
            SD.setStatus(0);
            SD.setDescription("User cannot have less than 1 card");
            returnMap.put("ReturnStatus", SD);
            return returnMap;
        }
        Paymentcard p = paymentcardRepository.findByLastfourdigits(lastfourdigits);
        paymentcardRepository.delete(p);
        Iterable<Paymentcard> pList2 = paymentcardRepository.findAllByUser(n);
        List<Login_Pay> paymentcardlist2 = new ArrayList<>();
        for(Paymentcard pay : pList2){
            paymentcardlist2.add(new Login_Pay(pay.getType(),pay.getExpirationdate(),pay.getBillingaddress(),pay.getLastfourdigits(),pay.getBillingcity(),pay.getBillingstate(),pay.getBillingzipcode()));
        }
        returnMap.put("paymentCardList", paymentcardlist2);
        SD.setStatus(1);
        SD.setDescription("Card successfully deleted");
        returnMap.put("ReturnStatus", SD);
        Email email2 = new Email();
        email2.editProfileNotification(n);
        return returnMap;
    }

    @ResponseBody
    @RequestMapping("changebilling")
    public Map<String, Object> changebilling(@RequestParam("email") String email, @RequestParam("lastfourdigits") String lastfourdigits, Paymentcard paymentcard){
        Map<String, Object> returnMap = new HashMap<>();
        StatusNDescription SD = new StatusNDescription();
        User n = userRepository.findByEmail(email);
        Paymentcard p = paymentcardRepository.findByLastfourdigits(lastfourdigits);

        p.setBillingaddress(paymentcard.getBillingaddress());
        p.setBillingcity(paymentcard.getBillingcity());
        p.setBillingstate(paymentcard.getBillingstate());
        p.setBillingzipcode(paymentcard.getBillingzipcode());

        paymentcardRepository.save(p);

        Iterable<Paymentcard> pList2 = paymentcardRepository.findAllByUser(n);
        List<Login_Pay> paymentcardlist2 = new ArrayList<>();
        for(Paymentcard pay : pList2){
            paymentcardlist2.add(new Login_Pay(pay.getType(),pay.getExpirationdate(),pay.getBillingaddress(),pay.getLastfourdigits(),pay.getBillingcity(),pay.getBillingstate(),pay.getBillingzipcode()));
        }
        returnMap.put("paymentCardList", paymentcardlist2);
        SD.setStatus(1);
        SD.setDescription("Card successfully updated");
        returnMap.put("ReturnStatus", SD);
        Email email2 = new Email();
        email2.editProfileNotification(n);
        return returnMap;
    }
}
