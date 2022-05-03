package com.csci6050.ebooking.repository;

import com.csci6050.ebooking.entity.Booking;
import com.csci6050.ebooking.entity.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {

    Iterable<Ticket> findAllByBooking(Booking booking);
}