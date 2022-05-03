package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.DTO.StatusNDescription;
import com.csci6050.ebooking.entity.Booking;
import com.csci6050.ebooking.entity.Movie;
import com.csci6050.ebooking.entity.ShowSchedule;
import com.csci6050.ebooking.entity.Ticket;
import com.csci6050.ebooking.repository.BookingRepository;
import com.csci6050.ebooking.repository.ShowScheduleRepository;
import com.csci6050.ebooking.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    // Form adding a movie to the repository (check if the title exist first)
    @ResponseBody
    @RequestMapping("getreserved")
    public Map<String, Object> getreservedseat(@RequestParam("showtimeid") int showtimeid){

        Map<String, Object> returnMap = new HashMap<>();
        StatusNDescription SD = new StatusNDescription();

        List<String> reservedlist = new ArrayList<>();

        ShowSchedule showSchedule = showScheduleRepository.findById(showtimeid);

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

        SD.setStatus(1);
        SD.setDescription("List of seat returned");
        returnMap.put("ReturnStatus", SD);
        returnMap.put("ReservedSeats",reservedlist);

        return returnMap;
    }
}
