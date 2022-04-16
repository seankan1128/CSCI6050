package com.csci6050.ebooking.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.csci6050.ebooking.entity.Movie;

public interface MovieRepository extends CrudRepository<Movie, Integer>  {
    Movie findById(int id);
    Movie findMovieByTitle(String title);
}
