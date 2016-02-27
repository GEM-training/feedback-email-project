package com.gem.nhom1.feedbackemail.screen.customer.listproduct;

import com.gem.nhom1.feedbackemail.base.BasePresenter;
import com.gem.nhom1.feedbackemail.network.entities.Store;

/**
 * Created by nghicv on 25/02/2016.
 */
public interface ProductListPresenter extends BasePresenter{
    void doLoadListProduct(Store store, int page, int size);
}
