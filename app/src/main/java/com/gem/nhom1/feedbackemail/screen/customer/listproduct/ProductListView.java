package com.gem.nhom1.feedbackemail.screen.customer.listproduct;

import com.gem.nhom1.feedbackemail.base.BaseView;

/**
 * Created by nghicv on 25/02/2016.
 */
public interface ProductListView extends BaseView<ProductListPresenter> {
    public void onLoadProductListSuccess(ProductListAdapter adapter);
}
