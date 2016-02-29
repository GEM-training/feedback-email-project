package com.gem.nhom1.feedbackemail.screen.customer.listproduct;

import com.gem.nhom1.feedbackemail.base.BaseView;
import com.gem.nhom1.feedbackemail.commom.util.DeviceUtils;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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

        if(NetworkUtil.isNetworkAvaiable(productListView.getContextBase())) {
            productListView.showProgress();
            ServiceBuilder.getService().getProductByStore(Session.getCurrentUser().getToken() , DeviceUtils.getDeviceId(productListView.getContextBase()), store.getId() , page , size).enqueue(baseCallback);
        } else {

           productListView.onRequestError(Constant.NET_WORK_ERROR);

        }
    }

    private Callback<ListProductDTO> baseCallback = new Callback<ListProductDTO>() {
        @Override
        public void onResponse(Call<ListProductDTO> call, Response<ListProductDTO> response) {
            if(response.isSuccess()){
                ListProductDTO listProductDTO = response.body();
                productListView.onLoadProductListSuccess(new ArrayList<Product>(Arrays.asList(listProductDTO.getContent())));
                productListView.onRequestSuccess();

            } else {
                productListView.onRequestError(response.code() + " , " + response.message());
            }
        }

        @Override
        public void onFailure(Call<ListProductDTO> call, Throwable t) {
                productListView.onRequestError("Connect to server error !");
        }
    };

}
