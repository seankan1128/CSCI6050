package com.csci6050.ebooking.DTO;

import com.csci6050.ebooking.entity.Paymentcard;
import com.csci6050.ebooking.entity.User;

import java.util.List;

public class login_UP {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String birthday;
    private String enrolledForPromotions;
    private int userType;
    private List<Paymentcard> PaymentCardList;

    public String getFirstname() {
        return firstName;
    }

    public void setFirstname(String firstname) {
        this.firstName = firstname;
    }

    public String getLastname() {
        return lastName;
    }

    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEnrolledForPromotions() {
        return enrolledForPromotions;
    }

    public void setEnrolledForPromotions(String enrolledForPromotions) {
        this.enrolledForPromotions = enrolledForPromotions;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public List<Paymentcard> getPaymentCardList() {
        return PaymentCardList;
    }

    public void setPaymentCardList(List<Paymentcard> paymentCardList) {
        PaymentCardList = paymentCardList;
    }
}
