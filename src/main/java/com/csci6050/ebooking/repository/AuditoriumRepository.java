package com.csci6050.ebooking.repository;

import com.csci6050.ebooking.entity.Auditorium;
import org.springframework.data.repository.CrudRepository;

public interface AuditoriumRepository extends CrudRepository<Auditorium, Integer> {
    Auditorium findAuditoriumByAudname(String audname);
}
