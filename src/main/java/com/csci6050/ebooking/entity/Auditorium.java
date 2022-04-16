package com.csci6050.ebooking.entity;

import javax.persistence.*;

@Entity
@Table(name = "auditorium")
public class Auditorium {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="auditoriumid")
    private int id;
    @Column(name="audname")
    private String audname;
    @Column(name="noofseats")
    private int noofseats;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAudname() {
        return audname;
    }

    public void setAudname(String audname) {
        this.audname = audname;
    }

    public int getNoofseats() {
        return noofseats;
    }

    public void setNoofseats(int noofseats) {
        this.noofseats = noofseats;
    }
}
