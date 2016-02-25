package com.gem.nhom1.feedbackemail.network.entities;

/**
 * Created by nghicv on 25/02/2016.
 */
public class UnitPrice {
    private Unit unit;
    private int price;

    public UnitPrice(Unit unit, int price) {
        this.unit = unit;
        this.price = price;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
