package com.gem.nhom1.feedbackemail.screen.customer.listproduct;

import com.gem.nhom1.feedbackemail.commom.util.NetworkUtil;
import com.gem.nhom1.feedbackemail.network.Session;
import com.gem.nhom1.feedbackemail.network.entities.Product;
import com.gem.nhom1.feedbackemail.network.entities.Store;
import com.gem.nhom1.feedbackemail.commom.Constant;
import com.gem.nhom1.feedbackemail.network.ServiceBuilder;
import com.gem.nhom1.feedbackemail.network.callback.BaseCallback;
import com.gem.nhom1.feedbackemail.network.dto.ListProductDTO;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by nghicv on 25/02/2016.
 */
public class ProductListPresenterImp implements ProductListPresenter {
    private ProductListView productListView;

    private int sizeDefault = 10;

    private int storeId;

    public ProductListPresenterImp(ProductListView productListView) {
        this.productListView = productListView;
    }


    @Override
    public void doLoadListProduct(Store store , int page , int size) {
        productListView.showProgress();

        storeId = (int) store.getId();

        if(!NetworkUtil.isNetworkAvaiable(productListView.getContextBase())) {
            ServiceBuilder.getService().getProductByStore(Session.getCurrentUser().getToken() , store.getId() , page , size).enqueue(baseCallback);
        } else {

           productListView.onRequestError(Constant.NET_WORK_ERROR);

        }
    }

    private BaseCallback baseCallback = new BaseCallback<ListProductDTO>() {
        @Override
        public void onError(int errorCode, String errorMessage) {
            productListView.onRequestError(errorMessage);
        }

        @Override
        public void onResponse(Object o) {
            productListView.onRequestSuccess();

            ListProductDTO listProductDTO = new Gson().fromJson(new Gson().toJson(o) , ListProductDTO.class);

            productListView.onLoadProductListSuccess(new ArrayList<Product>(Arrays.asList(listProductDTO.getContent())));
        }
    };

}
