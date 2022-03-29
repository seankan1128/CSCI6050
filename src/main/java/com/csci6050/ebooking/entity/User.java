package com.csci6050.ebooking.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="userid")
    private int id;
    @Column(name="firstname")
    private String firstName;
    @Column(name="lastname")
    private String lastName;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="phone")
    private String phone;
    @Column(name="status")
    private int status;
    @Column(name="enrolledforpromotions")
    private String enrolledForPromotions;
    @Column(name="usertype")
    private int userType;
    @Column(name="birthday")
    private String birthday;

//    @OneToMany (mappedBy = "user")
//    private List<Paymentcard> paymentcardList;

    @Column(name = "verificationcode", length = 64)
    private String verificationCode;

    @Column(name = "pwresetcode", length = 64)
    private String pwresetcode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

//    public List<Paymentcard> getPaymentcardList() {
//        return paymentcardList;
//    }
//
//    public void setPaymentcardList(List<Paymentcard> paymentcardList) {
//        this.paymentcardList = paymentcardList;
//    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPwresetcode() {
        return pwresetcode;
    }

    public void setPwresetcode(String pwresetcode) {
        this.pwresetcode = pwresetcode;
    }
}