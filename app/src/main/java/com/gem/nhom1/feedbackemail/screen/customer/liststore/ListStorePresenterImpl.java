package com.gem.nhom1.feedbackemail.screen.customer.liststore;


import android.util.Log;

import com.gem.nhom1.feedbackemail.commom.util.DeviceUtils;
import com.gem.nhom1.feedbackemail.commom.util.NetworkUtil;
import com.gem.nhom1.feedbackemail.network.Session;
import com.gem.nhom1.feedbackemail.network.dto.ListProductDTO;
import com.gem.nhom1.feedbackemail.network.dto.ListStoreDTO;
import com.gem.nhom1.feedbackemail.base.BaseView;
import com.gem.nhom1.feedbackemail.commom.Constant;
import com.gem.nhom1.feedbackemail.network.ServiceBuilder;
import com.gem.nhom1.feedbackemail.network.callback.BaseCallback;
import com.gem.nhom1.feedbackemail.network.entities.Store;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by phuongtd on 24/02/2016.
 */
public class ListStorePresenterImpl implements ListStorePresenter {
    private ListStoreView mView;
    private BaseView baseView;

    public ListStorePresenterImpl(ListStoreView view){
        this.mView = view;
        baseView = (BaseView) mView.getContextBase();

    }


    @Override
    public void onLoadMore(int page,int pageSize) {
        baseView.showProgress();
        if(NetworkUtil.isNetworkAvaiable(mView.getContextBase())){
            Log.d("phuongtd", "Token On Load Store" + Session.getCurrentUser().getToken());
            ServiceBuilder.getService().getStore(Session.getCurrentUser().getToken(), DeviceUtils.getDeviceId(baseView.getContextBase()) , page ,pageSize).enqueue(mCallbackMore);
        } else {
           baseView.onRequestError(Constant.NET_WORK_ERROR);
        }

    }

    private Callback<ListStoreDTO> mCallbackMore = new Callback<ListStoreDTO>() {
        @Override
        public void onResponse(Call<ListStoreDTO> call, Response<ListStoreDTO> response) {
           if(response.isSuccess()){
               baseView.onRequestSuccess();

               ListStoreDTO listStoreDTO = response.body();

               mView.onLoadDealerSuccess(new ArrayList<Store>(Arrays.asList(listStoreDTO.getContent())));
           } else {
               baseView.onRequestError(response.message());
           }
        }

        @Override
        public void onFailure(Call<ListStoreDTO> call, Throwable t) {

        }
    };

}
