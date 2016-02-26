package com.gem.nhom1.feedbackemail.network.entities;

/**
 * Created by nghicv on 25/02/2016.
 */
public class Unit {


    private int unitId;
    private String type;
    private int isPart;
    private Unit unit;

    public Unit(){

    }

    public Unit(int unitId, String type, int isPart, Unit unit) {
        this.unitId = unitId;
        this.type = type;
        this.isPart = isPart;
        this.unit = unit;
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

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
