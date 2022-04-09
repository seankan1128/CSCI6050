package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.entity.Promotions;
import com.csci6050.ebooking.entity.User;
import com.csci6050.ebooking.repository.PromotionsRepository;
import com.csci6050.ebooking.repository.UserRepository;
import com.csci6050.ebooking.tool.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller // This means that this class is a Controller
public class promotionController {
    @Autowired
    private PromotionsRepository promotionsRepository;

    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    @RequestMapping("promoform")
    public int addNewPromotion(Promotions promo){
        System.out.println(promo.getPromoCode());
        Promotions p = promotionsRepository.findByPromoCode(promo.getPromoCode());
        if(p != null){
            System.out.println("Promo already exists");
            return 0;
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

        return 1;
    }

}
