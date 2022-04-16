package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.DTO.StatusNDescription;
import com.csci6050.ebooking.entity.Paymentcard;
import com.csci6050.ebooking.entity.Promotions;
import com.csci6050.ebooking.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import com.csci6050.ebooking.entity.Movie;
import com.csci6050.ebooking.repository.MovieRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

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
        m.setCategory(movie.getCategory());
        m.setGenre(movie.getGenre());
        m.setDirector(movie.getDirector());
        m.setDuration(movie.getDuration());
//        m.setTrailerPicture(movie.getTrailerPicture());
        m.setTrailerVideo(movie.getTrailerVideo());
        m.setReview(movie.getReview());
        m.setRatingID(movie.getRatingID());
        m.setDescription(movie.getDescription());

        movieRepository.save(m);
        Iterable<Movie> mlist = movieRepository.findAll();
        List<Movie> movieList = new ArrayList<>();
        mlist.forEach(movieList::add);
        SD.setStatus(1);
        SD.setDescription("Successfully saved");
        returnMap.put("ReturnStatus",SD);
        returnMap.put("MovieList",movieList);
        return returnMap;
    }

    @ResponseBody
    @RequestMapping("addmovieform2")
    public Map<String, Object> returnMovie(Movie movie){
        Map<String, Object> returnMap = new HashMap<>();
        StatusNDescription SD = new StatusNDescription();
        Iterable<Movie> mlist = movieRepository.findAll();
        List<Movie> movieList = new ArrayList<>();
        mlist.forEach(movieList::add);
        SD.setStatus(1);
        SD.setDescription("returned all movie");
        returnMap.put("ReturnStatus",SD);
        returnMap.put("MovieList",movieList);
        return returnMap;
    }

    @ResponseBody
    @RequestMapping("addmovieform3")
    public Map<String, Object> setMovieImage(@RequestParam("trailerlink") String trailerlink, @RequestParam("title") String title, @RequestParam("image") MultipartFile image, @RequestParam("image2") MultipartFile image2) throws IOException {
        Map<String, Object> returnMap = new HashMap<>();
        StatusNDescription SD = new StatusNDescription();

        Movie m = movieRepository.findMovieByTitle(title);

        m.setTrailerVideo(trailerlink);

        int index = Objects.requireNonNull(image.getOriginalFilename()).lastIndexOf("//");
        String filename = image.getOriginalFilename().substring(index+1);
        byte[] bytes = image.getBytes();
        String path = "src//main//java//com//csci6050//ebooking//images//"+filename;
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(path)));
        bufferedOutputStream.write(bytes);
        bufferedOutputStream.close();

        m.setTrailerPicture(path);

        int index2 = Objects.requireNonNull(image2.getOriginalFilename()).lastIndexOf("//");
        String filename2 = image2.getOriginalFilename().substring(index2+1);
        byte[] bytes2 = image2.getBytes();
        String path2 = "src//main//java//com//csci6050//ebooking//images//"+filename2;
        BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(new File(path2)));
        bufferedOutputStream2.write(bytes2);
        bufferedOutputStream2.close();

        m.setTrailerbanner(path2);

        movieRepository.save(m);

        SD.setStatus(1);
        SD.setDescription("successfully set image and trailer");
        returnMap.put("ReturnStatus",SD);
        return returnMap;
    }

}
