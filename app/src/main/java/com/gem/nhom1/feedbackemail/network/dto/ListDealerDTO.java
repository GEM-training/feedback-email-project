package com.gem.nhom1.feedbackemail.network.dto;

import com.gem.nhom1.feedbackemail.base.BaseDTO;
import com.gem.nhom1.feedbackemail.network.entities.Dealer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by phuongtd on 24/02/2016.
 */
public class ListDealerDTO extends BaseDTO implements Serializable {
    List<Dealer> dealerList = new ArrayList<Dealer>();

    public ListDealerDTO() {
    }

    public ListDealerDTO(List<Dealer> dealerList) {
        this.dealerList = dealerList;
    }

    public List<Dealer> getDealerList() {
        return dealerList;
    }

    public void setDealerList(List<Dealer> dealerList) {
        this.dealerList = dealerList;
    }
}
