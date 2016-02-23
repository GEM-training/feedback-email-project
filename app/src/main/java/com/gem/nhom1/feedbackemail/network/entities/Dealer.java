package com.gem.nhom1.feedbackemail.network.entities;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by phuong on 1/19/2016.
 */
public class Dealer {

    private int dealerId;

    private String name;

    private String address;

    public Dealer() {
    }

    public Dealer(int dealerId, String name, String address) {
        this.dealerId = dealerId;
        this.name = name;
        this.address = address;
    }

    public int getDealerId() {
        return dealerId;
    }

    public void setDealerId(int dealerId) {
        this.dealerId = dealerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
