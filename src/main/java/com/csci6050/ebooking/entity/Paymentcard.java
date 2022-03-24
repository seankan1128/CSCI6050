package com.csci6050.ebooking.entity;

import org.springframework.data.annotation.Reference;

import javax.persistence.*;

@Entity
@Table(name = "paymentcard")
public class Paymentcard {
    @Id
    @Column(name="cardno")
    private String cardno;
    @ManyToOne
    @JoinColumn(name="userid")
    private User user;
    @Column(name="type")
    private String type;
    @Column(name="expirationdate")
    private String expirationdate;
    @Column(name="billingaddress")
    private String billingaddress;

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(String expirationdate) {
        this.expirationdate = expirationdate;
    }

    public String getBillingaddress() {
        return billingaddress;
    }

    public void setBillingaddress(String billingaddress) {
        this.billingaddress = billingaddress;
    }
}
