package com.csci6050.ebooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Class userController: Connection between back-end logic layer and front-end.
 */
@Controller
public class registrationController {

    /**
     * The registration page of the system
     * @return HTML file name
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage(){
        return "register";
    }
//
    @RequestMapping(value = "/")
    public String mainPage() {return "dsa";}

//    @RequestMapping("/channel/query")
//    public String Query(@RequestParam("query") String query, Model model){
//        if (user.getUserID(query) != null){
//            return "register";
//        }
//        else if (channelService.getChannelByFollowerName(query).size() != 0){
//            model.addAttribute("followerChannelList", channelService.getChannelByFollowerName(query));
//            model.addAttribute("followerDetail", channelService.getFollowerByFollowerName(query));
//            return "followerDetail";
//        }
//        else
//            return "register";
//    }
}
