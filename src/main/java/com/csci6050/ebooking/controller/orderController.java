package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.DTO.StatusNDescription;
import com.csci6050.ebooking.entity.Booking;
import com.csci6050.ebooking.entity.ShowSchedule;
import com.csci6050.ebooking.entity.Ticket;
import com.csci6050.ebooking.entity.User;
import com.csci6050.ebooking.repository.*;
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

    @Autowired
    private ShowScheduleRepository showScheduleRepository;

    @Autowired
    private PaymentcardRepository paymentcardRepository;

    @Autowired
    private PromotionsRepository promotionsRepository;

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
        for(Booking booking : bookingList){
            Iterable<Ticket> tlist = ticketRepository.findAllByBooking(booking);
            List<Ticket> ticketList = new ArrayList<>();
            tlist.forEach(ticketList::add);
            Map<String, Object> bookingListMap = new HashMap<>();
            bookingListMap.put("Booking",booking);
            bookingListMap.put("Tickets",ticketList);
            returnBookingMap.add(bookingListMap);
        }


//        Booking bookingtest = new Booking();
//        bookingtest.setTotalprice(333);
//        bookingtest.setDateofbooking("1648180800000");
//        bookingtest.setAddress("fdsfsdfsfds00");
//        bookingtest.setNooftickets(4);
//        ShowSchedule showScheduletest = showScheduleRepository.findById(13);
//        bookingtest.setShowSchedule(showScheduletest);
//        bookingtest.setUser(user);
//        bookingtest.setId(1);
//        bookingtest.setPromotions(promotionsRepository.findByPromoCode("test2"));
//        bookingtest.setPaymentcard(paymentcardRepository.findByLastfourdigits("0000"));
//
//        Ticket tickettest = new Ticket();
//        tickettest.setPrice(12);
//        tickettest.setType(2);
//        tickettest.setBooking(bookingtest);
//        tickettest.setSeatId("1_1");
//        tickettest.setDate("1648180800000");
//
//        Ticket tickettest2 = new Ticket();
//        tickettest2.setPrice(22);
//        tickettest2.setType(2);
//        tickettest2.setBooking(bookingtest);
//        tickettest2.setSeatId("1_2");
//        tickettest2.setDate("1648180800000");
//
//        List<Ticket> ticketList = new ArrayList<>();
//        ticketList.add(tickettest);
//        ticketList.add(tickettest2);
//
//        Map<String, Object> bookingListMap = new HashMap<>();
//        bookingListMap.put("Booking",bookingtest);
//        bookingListMap.put("Tickets",ticketList);
//        returnBookingMap.add(bookingListMap);
//        returnBookingMap.add(bookingListMap);


        SD.setStatus(1);
        SD.setDescription("Order history returned");
        returnMap.put("ReturnStatus", SD);
        returnMap.put("OrderHistory", returnBookingMap);
        return returnMap;
    }
}
