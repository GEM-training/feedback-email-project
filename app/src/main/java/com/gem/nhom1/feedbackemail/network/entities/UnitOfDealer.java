package com.gem.nhom1.feedbackemail.network.entities;

/**
 * Created by phuongtd on 25/02/2016.
 */
public class UnitOfDealer {
    private int unitId;

    private int dealerId;

    private double price;

    public UnitOfDealer() {

    }

    public UnitOfDealer(int unitId, int dealerId, double price) {
        this.unitId = unitId;
        this.dealerId = dealerId;
        this.price = price;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
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
