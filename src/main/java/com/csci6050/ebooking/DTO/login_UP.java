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
    private int userStatus;
    private int userType;
    private List<Login_Pay> PaymentCardList;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public List<Login_Pay> getPaymentCardList() {
        return PaymentCardList;
    }

    public void setPaymentCardList(List<Login_Pay> paymentCardList) {
        PaymentCardList = paymentCardList;
    }
}
