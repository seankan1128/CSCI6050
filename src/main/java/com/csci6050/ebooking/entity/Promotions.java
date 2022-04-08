package com.csci6050.ebooking.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "promotions")
public class Promotions{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="promoid")
    private int id;
    @Column(name="promoCode")
    private String promoCode;
    @Column(name="promostart")
    private String promoStart;
    @Column(name="promoend")
    private String promoEnd;
    @Column(name="promodiscount")
    private float promoDiscount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getPromoStart() {
        return promoStart;
    }

    public void setPromoStart(String promoStart) {
        this.promoStart = promoStart;
    }

    public String getPromoEnd() {
        return promoEnd;
    }

    public void setPromoEnd(String promoEnd) {
        this.promoEnd = promoEnd;
    }

    public float getPromoDiscount() {
        return promoDiscount;
    }

    public void setPromoDiscount(float promoDiscount) {
        this.promoDiscount = promoDiscount;
    }
}