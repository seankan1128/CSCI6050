package com.csci6050.ebooking.repository;

import com.csci6050.ebooking.entity.Promotions;
import com.csci6050.ebooking.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
public interface Promotionsrepository extends CrudRepository<Promotions, Integer>{

}
