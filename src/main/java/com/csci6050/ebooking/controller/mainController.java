package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.entity.Movie;
import com.csci6050.ebooking.entity.ShowSchedule;
import com.csci6050.ebooking.repository.MovieRepository;
import com.csci6050.ebooking.repository.ShowScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller // This means that this class is a Controller
public class mainController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ShowScheduleRepository showScheduleRepository;

    @GetMapping("/")
    public String mainpage(){
        return "index";
    }

    @ResponseBody
    @RequestMapping("movielist")
    public Map<String, Object> main(){
        Map<String, Object> returnMap = new HashMap<>();

        Iterable<ShowSchedule> sList = showScheduleRepository.findAll();
        List<ShowSchedule> showscheduleList = new ArrayList<>();
        sList.forEach(showscheduleList::add);

        Iterable<Movie> mList = movieRepository.findAll();
        List<Movie> movieList = new ArrayList<>();
        mList.forEach(movieList::add);

        List<Movie> in_schedule_movielist = new ArrayList<>();
        // Traverse through the first list
        for (ShowSchedule s : showscheduleList) {

            // If this element is not present in newList
            // then add it
            if (!in_schedule_movielist.contains(movieRepository.findById(s.getId()))) {

                in_schedule_movielist.add(movieRepository.findById(s.getId()));
            }
        }

        List<Movie> not_in_schedule_movielist = new ArrayList<>(movieList);
        not_in_schedule_movielist.removeAll(in_schedule_movielist);

        returnMap.put("ComingSoon", not_in_schedule_movielist);
        returnMap.put("StreamingNow", in_schedule_movielist);


        return returnMap;
    }

}
