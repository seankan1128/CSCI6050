package csci.mvc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Class userController: Connection between back-end logic layer and front-end.
 */
public class userController {

    /**
     * The login page of the system
     * @return HTML file name
     */
    @RequestMapping({"/","/register"})
    public String registerPage(){
        return "register";
    }

    /**
     * login page: receive user information
     * @param query get from front-end submission.
     */
    @RequestMapping("/channel/query")
    public String Query(@RequestParam("query") String query, Model model){
        if (user.getUserID(query) != null){
//            int channel_id = channelService.getChannelIdByName(query);
            System.out.println(user.getUserID());
            return "register";
        }
//        else if (channelService.getChannelByFollowerName(query).size() != 0){
//            model.addAttribute("followerChannelList", channelService.getChannelByFollowerName(query));
//            model.addAttribute("followerDetail", channelService.getFollowerByFollowerName(query));
//            return "followerDetail";
//        }
        else
            return "register";
    }
}
