package com.gem.nhom1.feedbackemail.screen.customer.listproduct;

import android.util.Log;

import com.gem.nhom1.feedbackemail.SQLDatabase.DealerAdapter;
import com.gem.nhom1.feedbackemail.adapter.DealerListAdapter;
import com.gem.nhom1.feedbackemail.base.BaseView;
import com.gem.nhom1.feedbackemail.commom.Constant;
import com.gem.nhom1.feedbackemail.network.ServiceBuilder;
import com.gem.nhom1.feedbackemail.network.callback.BaseCallback;
import com.gem.nhom1.feedbackemail.network.dto.ListProductDTO;
import com.gem.nhom1.feedbackemail.network.entities.Dealer;
import com.gem.nhom1.feedbackemail.network.entities.UnitPrice;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nghicv on 25/02/2016.
 */
public class ProductListPresenterImp implements ProductListPresenter {
    private ProductListView productListView;
    private Dealer dealer;

    public ProductListPresenterImp(ProductListView productListView, Dealer dealer) {
        this.productListView = productListView;
        this.dealer = dealer;
    }


    @Override
    public void doLoadListProduct() {
            productListView.showProgress();
            ServiceBuilder.getService().getListProduct(Constant.CURRENT_ACCESS_TOKEN, dealer.getDealerId()).enqueue(baseCallback);

    }

    private BaseCallback baseCallback = new BaseCallback<ListProductDTO>() {
        @Override
        public void onError(int errorCode, String errorMessage) {
            productListView.onRequestError(errorCode, errorMessage);
        }

        @Override
        public void onResponse(Object o) {
            productListView.onRequestSuccess();
            List<UnitPrice> productListTemp = new Gson().fromJson(new Gson().toJson(o) , ( new ArrayList<UnitPrice>()).getClass());

            List<UnitPrice> list = new ArrayList<UnitPrice>();

            for(int i = 0 ; i< productListTemp.size() ; i++){
                list.add(new Gson().fromJson(new Gson().toJson(productListTemp.get(i)), UnitPrice.class));
            }
            ProductListAdapter productListAdapter = new ProductListAdapter(productListView.getContextBase() , list);

            productListView.onLoadProductListSuccess(productListAdapter);

        }
    };
}