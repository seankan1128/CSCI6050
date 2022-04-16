package com.csci6050.ebooking.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="movieid")
    private int id;
    @Column(name="title")
    private String title;
    @Column(name="category")
    private String category;
    @Column(name="cast")
    private String cast;
    @Column(name="genre")
    private String genre;
    @Column(name="director")
    private String director;
    @Column(name="duration")
    private String duration;
    @Column(name="trailerpicture")
    private String trailerPicture;
    @Column(name="trailerbanner")
    private String trailerbanner;
    @Column(name="trailervideo")
    private String trailerVideo;
    @Column(name="description")
    private String description;
    @Column(name="review")
    private int review;
    @Column(name="ratingid")
    private int ratingID;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTrailerPicture() {
        return trailerPicture;
    }

    public void setTrailerPicture(String trailerPicture) {
        this.trailerPicture = trailerPicture;
    }

    public String getTrailerVideo() {
        return trailerVideo;
    }

    public void setTrailerVideo(String trailerVideo) {
        this.trailerVideo = trailerVideo;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public int getRatingID() {
        return ratingID;
    }

    public void setRatingID(int ratingID) {
        this.ratingID = ratingID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrailerbanner() {
        return trailerbanner;
    }

    public void setTrailerbanner(String trailerbanner) {
        this.trailerbanner = trailerbanner;
    }
}
