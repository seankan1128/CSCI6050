package com.csci6050.ebooking.DTO;

import com.csci6050.ebooking.entity.Paymentcard;
import com.csci6050.ebooking.entity.User;

import java.util.List;

public class login_UPSD {
    private User user;
    private List<Paymentcard> PaymentCardList;
    private int Status;
    private String description;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Paymentcard> getPaymentCardList() {
        return PaymentCardList;
    }

    public void setPaymentCardList(List<Paymentcard> paymentCardList) {
        PaymentCardList = paymentCardList;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
