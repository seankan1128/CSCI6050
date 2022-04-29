package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.DTO.StatusNDescription;
import com.csci6050.ebooking.entity.*;
import com.csci6050.ebooking.repository.AuditoriumRepository;
import com.csci6050.ebooking.repository.ShowScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    private ShowScheduleRepository showScheduleRepository;

    @Autowired
    private AuditoriumRepository auditoriumRepository;

    @Autowired
    private MovieRepository movieRepository;

    // Form adding a movie to the repository (check if the title exist first)
    @ResponseBody
    @RequestMapping("addmovieform")
    public Map<String, Object> addNewMovie(Movie movie){

        Movie mov = movieRepository.findMovieByTitle(movie.getTitle());

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
        m.setReview(movie.getReview());
        m.setRatingID(movie.getRatingID());
        m.setDescription(movie.getDescription());
        m.setProducer(movie.getProducer());

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

    // returning all movie to the front
    @ResponseBody
    @RequestMapping("addmovieform2")
    public Map<String, Object> returnMovie(){
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

    // Form that upload movie image to existing movie
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
//        String path = "src//main//java//com//csci6050//ebooking//images//"+filename;
        String path = "src//main//resources//static//image//"+filename;
//        String path = "C:/Users/Sean/IdeaProjects/ebooking/src/main/resources/static/image/" + filename;
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(path)));
        bufferedOutputStream.write(bytes);
        bufferedOutputStream.close();

        m.setTrailerPicture(filename);

        int index2 = Objects.requireNonNull(image2.getOriginalFilename()).lastIndexOf("//");
        String filename2 = image2.getOriginalFilename().substring(index2+1);
        byte[] bytes2 = image2.getBytes();
//        String path2 = "src//main//java//com//csci6050//ebooking//images//"+filename2;
        String path2 = "src//main//resources//static//image//"+filename2;
//        String path2 = "C:/Users/Sean/IdeaProjects/ebooking/src/main/resources/static/image/" + filename2;
        BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(new File(path2)));
        bufferedOutputStream2.write(bytes2);
        bufferedOutputStream2.close();

        m.setTrailerbanner(filename2);

        movieRepository.save(m);

        SD.setStatus(1);
        SD.setDescription("successfully set image and trailer");
        returnMap.put("ReturnStatus",SD);
        return returnMap;
    }

    // Form that add show schedule to existing movie
    @ResponseBody
    @RequestMapping("addmovieform4")
    public Map<String, Object> saveshowschedule(@RequestParam("title") String title, @RequestParam("date") String date, @RequestParam("room") String room){
        Map<String, Object> returnMap = new HashMap<>();
        StatusNDescription SD = new StatusNDescription();
        Movie m = movieRepository.findMovieByTitle(title);
        Auditorium a = auditoriumRepository.findAuditoriumByAudname(room);

        //Add logic no crash in time at same showroom
        Iterable<ShowSchedule> slist = showScheduleRepository.findAllByAuditorium(a);
        List<ShowSchedule> showScheduleList = new ArrayList<>();
        slist.forEach(showScheduleList::add);

        Long startDate = Long.parseLong(date);
        Long endDate = Long.parseLong(date);
        endDate = endDate + m.getDuration()*60000;


        if (checkschedule(startDate, endDate, showScheduleList)) {
            ShowSchedule showSchedule = new ShowSchedule();
            showSchedule.setMovie(m);
            showSchedule.setAuditorium(a);
            showSchedule.setStarttime(startDate.toString());
            showSchedule.setEndtime(endDate.toString());
            showScheduleRepository.save(showSchedule);

            SD.setStatus(1);
            SD.setDescription("Show schedule saved");
        } else {
            SD.setStatus(0);
            SD.setDescription("Show schedule crashed");
        }
        returnMap.put("ReturnStatus", SD);
        return returnMap;
    }

    public boolean checkschedule(long starttime, long endtime, List<ShowSchedule> showScheduleList){
        for (ShowSchedule showSchedule : showScheduleList){
            if((starttime >= Long.parseLong(showSchedule.getStarttime()))&&(starttime < Long.parseLong(showSchedule.getEndtime()))){
                return false;
            }
            if((starttime < Long.parseLong(showSchedule.getStarttime()))&&(endtime > Long.parseLong(showSchedule.getStarttime()))){
                return false;
            }
        }
        return true;
    }
}
