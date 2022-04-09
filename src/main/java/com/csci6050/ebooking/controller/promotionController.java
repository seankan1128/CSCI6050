package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.DTO.StatusNDescription;
import com.csci6050.ebooking.entity.Paymentcard;
import com.csci6050.ebooking.entity.Promotions;
import com.csci6050.ebooking.entity.User;
import com.csci6050.ebooking.repository.PromotionsRepository;
import com.csci6050.ebooking.repository.UserRepository;
import com.csci6050.ebooking.tool.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller // This means that this class is a Controller
public class promotionController {

    @Autowired
    private PromotionsRepository promotionsRepository;

    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    @RequestMapping("promoform")
    public Map<String, Object> Promotionpage(Promotions promo,  @RequestParam("submittype") int submittype){
        if(submittype == 1) {
            Map<String, Object> returnMap = new HashMap<>();
            System.out.println(promo.getPromoCode());
            Promotions p = promotionsRepository.findByPromoCode(promo.getPromoCode());
            if (p != null) {
                System.out.println("Promo already exists");
                StatusNDescription SD = new StatusNDescription();
                SD.setDescription("Promo already exists");
                SD.setStatus(0);

                Iterable<Promotions> pList = promotionsRepository.findAll();
                List<Promotions> promotionsList = new ArrayList<>();
                pList.forEach(promotionsList::add);
                returnMap.put("ReturnStatus", SD);
                returnMap.put("PromotionList", promotionsList);
                return returnMap;
            }

            Promotions pr = new Promotions();
            pr.setPromoCode(promo.getPromoCode());
            pr.setPromoStart(promo.getPromoStart());
            pr.setPromoEnd(promo.getPromoEnd());
            pr.setPromoDiscount(promo.getPromoDiscount());
            pr.setPromoName(promo.getPromoName());

            promotionsRepository.save(pr);
            System.out.println("Promo has been saved");

            Iterable<User> userIterable = userRepository.findAllByEnrolledForPromotions("on");

            Email email = new Email();
            email.sendPromotion(userIterable, pr);

            StatusNDescription SD = new StatusNDescription();
            SD.setDescription("Promotion has been saved and sent");
            SD.setStatus(1);

            Iterable<Promotions> pList = promotionsRepository.findAll();
            List<Promotions> promotionsList = new ArrayList<>();
            pList.forEach(promotionsList::add);
            returnMap.put("ReturnStatus", SD);
            returnMap.put("PromotionList", promotionsList);
            return returnMap;
        }

        if(submittype == 0){
            Map<String, Object> returnMap = new HashMap<>();
            StatusNDescription SD = new StatusNDescription();
            SD.setDescription("Promotion list sent");
            SD.setStatus(1);

            Iterable<Promotions> pList = promotionsRepository.findAll();
            List<Promotions> promotionsList = new ArrayList<>();
            pList.forEach(promotionsList::add);
            returnMap.put("ReturnStatus", SD);
            returnMap.put("PromotionList", promotionsList);
            return returnMap;

        }
        Map<String, Object> returnMap = new HashMap<>();
        StatusNDescription SD = new StatusNDescription();
        SD.setDescription("Promotion list sent");
        SD.setStatus(1);

        Iterable<Promotions> pList = promotionsRepository.findAll();
        List<Promotions> promotionsList = new ArrayList<>();
        pList.forEach(promotionsList::add);
        returnMap.put("ReturnStatus", SD);
        returnMap.put("PromotionList", promotionsList);
        return returnMap;

    }

}
