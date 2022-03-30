package com.csci6050.ebooking.repository;

import com.csci6050.ebooking.entity.Paymentcard;
import com.csci6050.ebooking.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface PaymentcardRepository extends CrudRepository<Paymentcard, Integer> {

    public Iterable<Paymentcard> findAllByUser(User user);

    public Paymentcard findByLastfourdigits(String lastfourdigits);
}
