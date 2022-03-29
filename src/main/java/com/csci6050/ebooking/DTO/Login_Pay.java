package com.csci6050.ebooking.DTO;

import com.csci6050.ebooking.entity.User;


public class Login_Pay {
    private String type;
    private String expirationdate;
    private String billingaddress;
    private String lastfourdigits;
    private String billingcity;
    private String billingstate;
    private String billingzipcode;

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

    public Login_Pay(String type, String expirationdate, String billingaddress, String lastfourdigits, String billingcity, String billingstate, String billingzipcode) {
        this.type = type;
        this.expirationdate = expirationdate;
        this.billingaddress = billingaddress;
        this.lastfourdigits = lastfourdigits;
        this.billingcity = billingcity;
        this.billingstate = billingstate;
        this.billingzipcode = billingzipcode;
    }
}
