package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.DTO.StatusNDescription;
import com.csci6050.ebooking.entity.Booking;
import com.csci6050.ebooking.entity.Ticket;
import com.csci6050.ebooking.entity.User;
import com.csci6050.ebooking.repository.BookingRepository;
import com.csci6050.ebooking.repository.TicketRepository;
import com.csci6050.ebooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class orderController {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @ResponseBody
    @RequestMapping("getorder")
    public Map<String, Object> getreservedseat(@RequestParam("username") String username) {

        Map<String, Object> returnMap = new HashMap<>();
        StatusNDescription SD = new StatusNDescription();

        User user = userRepository.findByEmail(username);

        Iterable<Booking> blist = bookingRepository.findAllByUser(user);
        List<Booking> bookingList = new ArrayList<>();
        blist.forEach(bookingList::add);

        if(bookingList.isEmpty()){
            SD.setStatus(0);
            SD.setDescription("No order history");
            returnMap.put("ReturnStatus", SD);
            return returnMap;
        }

        List<Map> returnBookingMap = new ArrayList<>();
//        Map<Booking, List<Ticket>> bookingListMap = new HashMap<>();
        for(Booking booking : bookingList){
            Iterable<Ticket> tlist = ticketRepository.findAllByBooking(booking);
            List<Ticket> ticketList = new ArrayList<>();
            tlist.forEach(ticketList::add);
            Map<Booking, List<Ticket>> bookingListMap = new HashMap<>();
            bookingListMap.put(booking,ticketList);
            returnBookingMap.add(bookingListMap);
        }

        SD.setStatus(1);
        SD.setDescription("Order history returned");
        returnMap.put("ReturnStatus", SD);
        returnMap.put("OrderHistory", returnBookingMap);
        return returnMap;
    }
}
