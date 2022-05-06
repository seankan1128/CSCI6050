package com.csci6050.ebooking.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ticketid")
    private int id;
    @ManyToOne
    @JoinColumn(name="bookingid")
    private Booking booking;
    @Column(name="price")
    private float price;
    @Column(name="date")
    private String date;
    @Column(name="type")
    private int type;
    @Column(name="seatid")
    private String seatId;

    public Ticket(){};

    public Ticket(Booking booking, String date, String seatId){
        this.setBooking(booking);
        this.setDate(date);
        this.setSeatId(seatId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }
}
