package com.project.gem.feedbackemail.model;

import java.io.Serializable;

/**
 * Created by phuongtd on 16/02/2016.
 */

public class User implements Serializable {

    private int userId;


    private String username;


    private String password;

    private Staff staff;


    private Dealer dealer;


    private Customer customer;

    public User() {
    }

    public User(int userId, String username, String password, Staff staff, Dealer dealer, Customer customer) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.staff = staff;
        this.dealer = dealer;
        this.customer = customer;
    }

    public int getIdPersonOfUser(){
        if(dealer != null)
            return dealer.getDealerId();
        if(customer != null)
            return customer.getId();
        if(staff != null)
            return staff.getStaffId();
        return -1;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
