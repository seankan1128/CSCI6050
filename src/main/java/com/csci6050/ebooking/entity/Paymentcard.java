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
    @Column(name="lastfourdigits")
    private String lastfourdigits;
    @Column(name="securitycode")
    private String securitycode;
    @Column(name="billingcity")
    private String billingcity;
    @Column(name="billingstate")
    private String billingstate;
    @Column(name="billingzipcode")
    private String billingzipcode;


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

    public String getLastfourdigits() {
        return lastfourdigits;
    }

    public void setLastfourdigits(String lastfourdigits) {
        this.lastfourdigits = lastfourdigits;
    }

    public String getSecuritycode() {
        return securitycode;
    }

    public void setSecuritycode(String securitycode) {
        this.securitycode = securitycode;
    }

    public String getBillingcity() {
        return billingcity;
    }

    public void setBillingcity(String billingcity) {
        this.billingcity = billingcity;
    }

    public String getBillingstate() {
        return billingstate;
    }

    public void setBillingstate(String billingstate) {
        this.billingstate = billingstate;
    }

    public String getBillingzipcode() {
        return billingzipcode;
    }

    public void setBillingzipcode(String billingzipcode) {
        this.billingzipcode = billingzipcode;
    }

}
