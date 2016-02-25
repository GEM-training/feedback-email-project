package com.gem.nhom1.feedbackemail.network.dto;

import com.gem.nhom1.feedbackemail.network.entities.UnitPrice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nghicv on 25/02/2016.
 */
public class ListProductDTO {
    List<UnitPrice> unitPrices = new ArrayList<UnitPrice>();

    public ListProductDTO() {
    }

    public ListProductDTO(List<UnitPrice> unitPrices) {
        this.unitPrices = unitPrices;
    }

    public List<UnitPrice> getUnitPrices() {
        return unitPrices;
    }

    public void setUnitPrices(List<UnitPrice> unitPrices) {
        this.unitPrices = unitPrices;
    }
}
