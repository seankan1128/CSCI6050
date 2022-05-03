package com.csci6050.ebooking.controller;

import com.csci6050.ebooking.DTO.StatusNDescription;
import com.csci6050.ebooking.entity.Movie;
import com.csci6050.ebooking.entity.ShowSchedule;
import com.csci6050.ebooking.repository.MovieRepository;
import com.csci6050.ebooking.repository.ShowScheduleRepository;
import com.csci6050.ebooking.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

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
        if(sList != null){
            sList.forEach(showscheduleList::add);
        }

        Iterable<Movie> mList = movieRepository.findAll();
        List<Movie> movieList = new ArrayList<>();
        mList.forEach(movieList::add);

        List<Movie> in_schedule_movielist = new ArrayList<>();
        // Traverse through the first list
        for (ShowSchedule s : showscheduleList) {

            // If this element is not present in newList
            // then add it
            if (!in_schedule_movielist.contains(s.getMovie())) {

                in_schedule_movielist.add(s.getMovie());
            }
        }

        List<Movie> not_in_schedule_movielist = new ArrayList<>(movieList);
        not_in_schedule_movielist.removeAll(in_schedule_movielist);
        List<Movie> tail = movieList.subList(Math.max(movieList.size() - 3, 0), movieList.size());

        returnMap.put("ComingSoon", not_in_schedule_movielist);
        returnMap.put("StreamingNow", in_schedule_movielist);
        returnMap.put("LatestMovie", tail);

        return returnMap;
    }

    @ResponseBody
    @RequestMapping("search")
    public Map<String, Object> search(@RequestParam("searchtype") String searchtype, @RequestParam("searchtext") String searchtext){
        Map<String, Object> returnMap = new HashMap<>();
        StatusNDescription SD = new StatusNDescription();

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
            if (!in_schedule_movielist.contains(s.getMovie())) {

                in_schedule_movielist.add(s.getMovie());
            }
        }

        List<Movie> not_in_schedule_movielist = new ArrayList<>(movieList);
        not_in_schedule_movielist.removeAll(in_schedule_movielist);


        // 1 is searching title
        if(searchtype.equals("1")){
            List<Movie> in_schedule_searched_list = new ArrayList<>();
            List<Movie> not_in_schedule_searched_list = new ArrayList<>();

            for (Movie m : in_schedule_movielist) {

                // If this element is not present in newList
                // then add it
                if (m.getTitle().toLowerCase().contains(searchtext.toLowerCase())) {
                    in_schedule_searched_list.add(m);
                }
            }

            for (Movie m : not_in_schedule_movielist) {

                // If this element is not present in newList
                // then add it
                if (m.getTitle().toLowerCase().contains(searchtext.toLowerCase())) {
                    not_in_schedule_searched_list.add(m);
                }
            }

            SD.setDescription("The movies are as follow");
            SD.setStatus(1);

            if (in_schedule_searched_list.isEmpty() && not_in_schedule_searched_list.isEmpty()){
                SD.setDescription("No movie matched");
                SD.setStatus(0);
            }
            returnMap.put("In_schedule_searched_list", in_schedule_searched_list);
            returnMap.put("Not_in_schedule_searched_list", not_in_schedule_searched_list);
            returnMap.put("ReturnStatus", SD);
        } else {
            List<Movie> in_schedule_searched_list = new ArrayList<>();
            List<Movie> not_in_schedule_searched_list = new ArrayList<>();

            for (Movie m : in_schedule_movielist) {

                // If this element is not present in newList
                // then add it
                if (m.getCategory().toLowerCase().contains(searchtext.toLowerCase())) {
                    in_schedule_searched_list.add(m);
                }
            }

            for (Movie m : not_in_schedule_movielist) {

                // If this element is not present in newList
                // then add it
                if (m.getCategory().toLowerCase().contains(searchtext.toLowerCase())) {
                    not_in_schedule_searched_list.add(m);
                }
            }

            SD.setDescription("The movies are as follow");
            SD.setStatus(1);

            if (in_schedule_searched_list.isEmpty() && not_in_schedule_searched_list.isEmpty()){
                SD.setDescription("No movie matched");
                SD.setStatus(0);
            }
            returnMap.put("In_schedule_searched_list", in_schedule_searched_list);
            returnMap.put("Not_in_schedule_searched_list", not_in_schedule_searched_list);
            returnMap.put("ReturnStatus", SD);
        }
        return returnMap;
    }

    @ResponseBody
    @RequestMapping("movieinfo")
    public Map<String, Object> schedulelist(@RequestParam("movietitle") String movietitle){
        Map<String, Object> returnMap = new HashMap<>();
        StatusNDescription SD = new StatusNDescription();
        Movie m = movieRepository.findMovieByTitle(movietitle);

        Iterable<ShowSchedule> sList = showScheduleRepository.findAllByMovie(m);
        List<ShowSchedule> showscheduleList = new ArrayList<>();
        sList.forEach(showscheduleList::add);

        if(showscheduleList.isEmpty()){
            SD.setStatus(0);
            SD.setDescription("The movie is not streaming now");
            returnMap.put("ReturnStatus", SD);
            return returnMap;
        }

        SD.setStatus(1);
        SD.setDescription("The show schedules are as follow");

        returnMap.put("ReturnStatus", SD);
        returnMap.put("ScheduleList", showscheduleList);

        return returnMap;
    }
}
