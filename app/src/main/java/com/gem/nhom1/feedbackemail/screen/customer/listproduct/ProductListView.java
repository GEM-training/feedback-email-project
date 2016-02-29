package com.gem.nhom1.feedbackemail.screen.customer.listproduct;

import com.gem.nhom1.feedbackemail.base.BaseView;
import com.gem.nhom1.feedbackemail.network.entities.Product;
import java.util.List;

/**
 * Created by nghicv on 25/02/2016.
 */
public interface ProductListView extends BaseView<ProductListPresenter> {
    void onLoadProductListSuccess(List<Product> products);
}
