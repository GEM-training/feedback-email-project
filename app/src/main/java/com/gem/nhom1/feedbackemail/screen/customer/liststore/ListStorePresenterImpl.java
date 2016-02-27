package com.gem.nhom1.feedbackemail.screen.customer.liststore;


import com.gem.nhom1.feedbackemail.commom.util.NetworkUtil;
import com.gem.nhom1.feedbackemail.network.Session;
import com.gem.nhom1.feedbackemail.network.dto.ListStoreDTO;
import com.gem.nhom1.feedbackemail.base.BaseView;
import com.gem.nhom1.feedbackemail.commom.Constant;
import com.gem.nhom1.feedbackemail.network.ServiceBuilder;
import com.gem.nhom1.feedbackemail.network.callback.BaseCallback;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;


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
            ServiceBuilder.getService().getStore(Session.getCurrentUser().getToken() , page , pageSize).enqueue(mCallbackMore);
        } else {
           baseView.onRequestError(1 , Constant.NET_WORK_ERROR);
        }

    }

    private BaseCallback mCallbackMore = new BaseCallback<ListStoreDTO>() {
        @Override
        public void onError(int errorCode, String errorMessage) {
            baseView.onRequestError(errorCode, errorMessage);
        }
        @Override
        public void onResponse(Object o) {

            baseView.onRequestSuccess();

            ListStoreDTO listStoreDTO = new Gson().fromJson(new Gson().toJson(o) , ListStoreDTO.class);

            mView.onLoadDealerSuccess(new ArrayList<>(Arrays.asList(listStoreDTO.getContent())));

        }
    };

}
