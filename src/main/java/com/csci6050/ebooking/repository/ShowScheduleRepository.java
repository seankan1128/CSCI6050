package com.csci6050.ebooking.repository;

import com.csci6050.ebooking.entity.Auditorium;
import com.csci6050.ebooking.entity.Movie;
import com.csci6050.ebooking.entity.ShowSchedule;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
public interface ShowScheduleRepository extends CrudRepository<ShowSchedule, Integer> {

    Iterable<ShowSchedule> findAll();

    Iterable<ShowSchedule> findAllByAuditorium(Auditorium auditorium);

    Iterable<ShowSchedule> findAllByMovie(Movie movie);

    ShowSchedule findById(int showtimeid);
}
