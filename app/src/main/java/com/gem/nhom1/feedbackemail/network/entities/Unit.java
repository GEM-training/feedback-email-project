package com.gem.nhom1.feedbackemail.network.entities;

/**
 * Created by nghicv on 25/02/2016.
 */
public class Unit {

    private int id;
    private String type;
    private int isPart;
    private Unit unit;

    public Unit(){

    }

    public Unit(int id, String type, int isPart) {
        this.id = id;
        this.type = type;
        this.isPart = isPart;
    }

    public Unit(int id, String type, int isPart, Unit unit) {
        this.id = id;
        this.type = type;
        this.isPart = isPart;
        this.unit = unit;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
