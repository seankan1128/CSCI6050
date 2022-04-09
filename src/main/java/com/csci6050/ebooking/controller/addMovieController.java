package com.csci6050.ebooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import com.csci6050.ebooking.entity.Movie;
import com.csci6050.ebooking.repository.MovieRepository;

@Controller
public class addMovieController {
    @Autowired
    private MovieRepository movieRepository;

    @ResponseBody
    @RequestMapping("addmovieform")
    public int addNewMovie(Movie movie){
        Movie mov = movieRepository.findById(movie.getId());
        if(mov != null){
            System.out.println("Movie already exists");
            return 0;
        }

        Movie m = new Movie();
        m.setTitle(movie.getTitle());
        m.setCast(movie.getCast());
        m.setGenre(movie.getGenre());
        m.setProducer(movie.getProducer());
        m.setDuration(movie.getDuration());
        m.setTrailerPicture(movie.getTrailerPicture());
        m.setTrailerVideo(movie.getTrailerVideo());
        m.setReview(movie.getReview());
        m.setRatingID(movie.getRatingID());

        movieRepository.save(m);

        return 1;
    }
}
