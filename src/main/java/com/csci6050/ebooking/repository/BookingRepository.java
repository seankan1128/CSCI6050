package com.csci6050.ebooking.repository;

import com.csci6050.ebooking.entity.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Integer>  {

    Booking findById(int bookingId);
}
