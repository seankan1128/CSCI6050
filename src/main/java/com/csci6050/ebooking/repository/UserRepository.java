package com.csci6050.ebooking.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.csci6050.ebooking.entity.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
    User findByVerificationCode(String verificationcode);

    User findByPwresetcode(String pwresetcode);

    Iterable<User> findAllByEnrolledForPromotions(String enroll);

    User findById(int id);
}