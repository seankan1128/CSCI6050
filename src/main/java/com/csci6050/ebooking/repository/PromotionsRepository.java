package com.csci6050.ebooking.repository;

import com.csci6050.ebooking.entity.Promotions;
import org.springframework.data.repository.CrudRepository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
public interface PromotionsRepository extends CrudRepository<Promotions, Integer>{
    Promotions findByPromoCode(String promoCode);

    Promotions findByPromoName(String promoName);

}
