package com.gem.nhom1.feedbackemail.network.entities;

/**
 * Created by nghicv on 25/02/2016.
 */
public class UnitPrice {
    private Unit unit;
    private double price;

    public UnitPrice() {
    }

    public UnitPrice(Unit unit, double price) {
        this.unit = unit;
        this.price = price;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
