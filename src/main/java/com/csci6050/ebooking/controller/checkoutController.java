package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.DTO.Login_Pay;
import com.csci6050.ebooking.DTO.StatusNDescription;
import com.csci6050.ebooking.entity.*;
import com.csci6050.ebooking.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class checkoutController {

    @Autowired
    private ShowScheduleRepository showScheduleRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentcardRepository paymentcardRepository;

    @Autowired
    private PromotionsRepository promotionsRepository;

    @ResponseBody
    @RequestMapping("getreserved")
    public Map<String, Object> getreservedseat(@RequestParam("showtimeid") String showtimeid){

        Map<String, Object> returnMap = new HashMap<>();
        StatusNDescription SD = new StatusNDescription();

        List<String> reservedlist = new ArrayList<>();

        ShowSchedule showSchedule = showScheduleRepository.findById(Integer.parseInt(showtimeid));

        Iterable<Booking> blist = bookingRepository.findAllByShowSchedule(showSchedule);
        List<Booking> bookingList = new ArrayList<>();
        blist.forEach(bookingList::add);

        for(Booking book : bookingList){
            Iterable<Ticket> tlist = ticketRepository.findAllByBooking(book);
            List<Ticket> ticketList = new ArrayList<>();
            tlist.forEach(ticketList::add);

            for(Ticket ticket : ticketList) {
                reservedlist.add(ticket.getSeatId());
            }

        }

        reservedlist.add("1_6");
        SD.setStatus(1);
        SD.setDescription("List of seat returned");
        returnMap.put("ReturnStatus", SD);
        returnMap.put("ReservedSeats",reservedlist);

        return returnMap;
    }

    @ResponseBody
    @RequestMapping("getpaymethod")
    public Map<String, Object> getpaymentcardmethod(@RequestParam("userid") int userid){

        Map<String, Object> returnMap = new HashMap<>();
        StatusNDescription SD = new StatusNDescription();

        User user = userRepository.findById(userid);

        Iterable<Paymentcard> plist = paymentcardRepository.findAllByUser(user);
        List<Login_Pay> paymentcardList = new ArrayList<>();
        for(Paymentcard pay : plist){
            paymentcardList.add(new Login_Pay(pay.getType(),pay.getExpirationdate(),pay.getBillingaddress(),pay.getLastfourdigits(),pay.getBillingcity(),pay.getBillingstate(),pay.getBillingzipcode()));
        }

        SD.setStatus(1);
        SD.setDescription("return payment card info");

        returnMap.put("ReturnStatus",SD);
        returnMap.put("PaymentInfo",paymentcardList);

        return returnMap;
    }

    @ResponseBody
    @RequestMapping("createbooking")
    public Map<String, Object> createbooking(@RequestParam("seatidlist") List<String> seatidlist, @RequestParam("ticket")List<Integer> tickettypelist,
                                             @RequestParam("cardinfo") String cardlastfourdigit, @RequestParam("billingaddress") String billingadress,
                                             @RequestParam("promocode") String promocode, @RequestParam("showtimeid") String showtimeid, @RequestParam("username") String username){

        Map<String, Object> returnMap = new HashMap<>();
        StatusNDescription SD = new StatusNDescription();

        User user = userRepository.findByEmail(username);
        Paymentcard paymentcard = paymentcardRepository.findByLastfourdigits(cardlastfourdigit);
        Promotions promotions = promotionsRepository.findByPromoCode(promocode);
        ShowSchedule showSchedule = showScheduleRepository.findById(Integer.parseInt(showtimeid));

        long unixTime = Instant.now().getEpochSecond(); //timestamp

        int ticketsize = seatidlist.size();

        //create booking first
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShowSchedule(showSchedule);
        booking.setPaymentcard(paymentcard);
        booking.setNooftickets(ticketsize);
        float totalprice = 0;
        for(int i = 0; i < ticketsize; i++){
            if (tickettypelist.get(i) == 1){
                totalprice += 9.99;
            }
            if (tickettypelist.get(i) == 2){
                totalprice += 14.99;
            }if (tickettypelist.get(i) == 3){
                totalprice += 7.99;
            }
        }
        booking.setTotalprice(totalprice);
        if(promotions != null){
            booking.setPromotions(promotions);
            booking.setTotalprice(totalprice * (1 - promotions.getPromoDiscount()/100));
        }
        booking.setDateofbooking(String.valueOf(unixTime));
        booking.setAddress(billingadress);

        bookingRepository.save(booking); // save the booking


        for(int i = 0; i < ticketsize; i++){
            Ticket ticket = new Ticket(booking, showSchedule.getStarttime(), tickettypelist.get(i), seatidlist.get(i));
            if (tickettypelist.get(i) == 1){
                ticket.setPrice((float) 9.99);
            }
            if (tickettypelist.get(i) == 2){
                ticket.setPrice((float) 14.99);
            }if (tickettypelist.get(i) == 3){
                ticket.setPrice((float) 7.99);
            }
            ticketRepository.save(ticket);
        }// create tickets for booking

        SD.setStatus(1);
        SD.setDescription("return payment card info");

        returnMap.put("ReturnStatus",SD);
//        returnMap.put("PaymentInfo",paymentcardList);

        return returnMap;
    }

}
