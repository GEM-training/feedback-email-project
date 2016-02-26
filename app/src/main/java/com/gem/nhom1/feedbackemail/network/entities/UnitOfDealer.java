package com.gem.nhom1.feedbackemail.network.entities;

/**
 * Created by phuongtd on 25/02/2016.
 */
public class UnitOfDealer {
    private int unitId;
    private String type;
    private int isPart;
    private int parentId;

    private int dealerId;

    private double price;

    public UnitOfDealer() {
    }

    public UnitOfDealer(int unitId, String type, int isPart, int parentId, int dealerId, double price) {
        this.unitId = unitId;
        this.type = type;
        this.isPart = isPart;
        this.parentId = parentId;
        this.dealerId = dealerId;
        this.price = price;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIsPart() {
        return isPart;
    }

    public void setIsPart(int isPart) {
        this.isPart = isPart;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getDealerId() {
        return dealerId;
    }

    public void setDealerId(int dealerId) {
        this.dealerId = dealerId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
