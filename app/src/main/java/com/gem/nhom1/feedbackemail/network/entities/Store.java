package com.gem.nhom1.feedbackemail.network.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by qsoft on 2/22/16.
 */

public class Store implements Serializable {
    private long id;
    private String name;
    private String address;
    private Date createdDate;
    private Date updatedDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Store(long id, String name, String address, Date createdDate, Date updatedDate, String description) {

        this.id = id;
        this.name = name;
        this.address = address;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.description = description;
    }

    private String description;
}

