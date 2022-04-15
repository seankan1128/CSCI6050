package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.DTO.StatusNDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import com.csci6050.ebooking.entity.Movie;
import com.csci6050.ebooking.repository.MovieRepository;

import java.util.HashMap;
import java.util.Map;

@Controller
public class addMovieController {
    @Autowired
    private MovieRepository movieRepository;

    @ResponseBody
    @RequestMapping("addmovieform")
    public Map<String, Object> addNewMovie(Movie movie){
        Movie mov = movieRepository.findById(movie.getId());

        Map<String, Object> returnMap = new HashMap<>();
        StatusNDescription SD = new StatusNDescription();

        if(mov != null){
            System.out.println("Movie already exists");
            SD.setStatus(0);
            SD.setDescription("Movie already exists");
            returnMap.put("ReturnStatus",SD);
            return returnMap;
        }

        Movie m = new Movie();
        m.setTitle(movie.getTitle());
        m.setCast(movie.getCast());
        m.setGenre(movie.getGenre());
        m.setProducer(movie.getProducer());
        m.setDuration(movie.getDuration());
//        m.setTrailerPicture(movie.getTrailerPicture());
        m.setTrailerVideo(movie.getTrailerVideo());
        m.setReview(movie.getReview());
        m.setRatingID(movie.getRatingID());

        movieRepository.save(m);

        SD.setStatus(1);
        SD.setDescription("Successfully saved");
        returnMap.put("ReturnStatus",SD);
        return returnMap;
    }
}
