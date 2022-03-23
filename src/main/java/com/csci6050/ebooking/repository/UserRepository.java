package com.csci6050.ebooking.repository;

import org.springframework.data.repository.CrudRepository;

import com.csci6050.ebooking.entity.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
public class UserRepository extends CrudRepository<User, Integer> {

}
