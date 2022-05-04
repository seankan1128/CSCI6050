package com.csci6050.ebooking.entity;

import javax.persistence.*;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="bookingid")
    private int id;
    @ManyToOne
    @JoinColumn(name="userid")
    private User user;
    @OneToOne
    @JoinColumn(name="showtimeid")
    private ShowSchedule showSchedule;
    @OneToOne
    @JoinColumn(name="cardid")
    private Paymentcard paymentcard;
    @Column(name="nooftickets")
    private int nooftickets;
    @Column(name="totalprice")
    private float totalprice;
    @OneToOne
    @JoinColumn(name="promoid")
    private Promotions promotions;
    @Column(name="dateofbooking")
    private String dateofbooking;
    @Column(name="address")
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ShowSchedule getShowSchedule() {
        return showSchedule;
    }

    public void setShowSchedule(ShowSchedule showSchedule) {
        this.showSchedule = showSchedule;
    }

    public Paymentcard getPaymentcard() {
        return paymentcard;
    }

    public void setPaymentcard(Paymentcard paymentcard) {
        this.paymentcard = paymentcard;
    }

    public int getNooftickets() {
        return nooftickets;
    }

    public void setNooftickets(int nooftickets) {
        this.nooftickets = nooftickets;
    }

    public float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(float totalprice) {
        this.totalprice = totalprice;
    }

    public Promotions getPromotions() {
        return promotions;
    }

    public void setPromotions(Promotions promotions) {
        this.promotions = promotions;
    }

    public String getDateofbooking() {
        return dateofbooking;
    }

    public void setDateofbooking(String dateofbooking) {
        this.dateofbooking = dateofbooking;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

