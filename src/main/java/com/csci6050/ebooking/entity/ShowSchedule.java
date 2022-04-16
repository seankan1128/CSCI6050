package com.csci6050.ebooking.entity;

import javax.persistence.*;

@Entity
@Table(name = "showschedule")
public class ShowSchedule {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="showtimeid")
    private int id;
    @ManyToOne
    @JoinColumn(name="movieid")
    private Movie movie;
    @ManyToOne
    @JoinColumn(name="auditoriumid")
    private Auditorium auditorium;
    @Column(name="starttime")
    private String starttime;
    @Column(name="endtime")
    private String endtime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}
