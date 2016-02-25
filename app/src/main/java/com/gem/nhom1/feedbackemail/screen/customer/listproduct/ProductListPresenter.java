package com.gem.nhom1.feedbackemail.screen.customer.listproduct;

import com.gem.nhom1.feedbackemail.base.BasePresenter;
import com.gem.nhom1.feedbackemail.network.entities.Dealer;

/**
 * Created by nghicv on 25/02/2016.
 */
public interface ProductListPresenter extends BasePresenter{
    public void doLoadListProduct(Dealer dealer);
}
