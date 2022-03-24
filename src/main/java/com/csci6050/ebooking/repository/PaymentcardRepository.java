package com.csci6050.ebooking.repository;

import com.csci6050.ebooking.entity.Paymentcard;
import com.csci6050.ebooking.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface PaymentcardRepository extends CrudRepository<Paymentcard, Integer> {

}
