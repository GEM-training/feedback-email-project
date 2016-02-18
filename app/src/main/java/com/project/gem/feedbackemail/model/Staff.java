package com.project.gem.feedbackemail.model;

/**
 * Created by vanhop on 1/18/16.
 */

public class Staff {


    private Integer staffId;


    private String name;


    private String phone;


    private String address;

    public Staff() {
    }

    public Staff(Integer staffId, String name, String phone, String address) {
        this.staffId = staffId;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}