package com.gem.nhom1.feedbackemail.screen.customer;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.gem.nhom1.feedbackemail.commom.Constant;
import com.gem.nhom1.feedbackemail.commom.util.DeviceUtils;
import com.gem.nhom1.feedbackemail.commom.util.NetworkUtil;
import com.gem.nhom1.feedbackemail.commom.util.PreferenceUtils;
import com.gem.nhom1.feedbackemail.network.ServiceBuilder;
import com.gem.nhom1.feedbackemail.network.callback.BaseCallback;
import com.gem.nhom1.feedbackemail.network.dto.TokenInfoDTO;
import com.gem.nhom1.feedbackemail.network.entities.Customer;
import com.gem.nhom1.feedbackemail.network.entities.UserInfo;
import com.google.gson.Gson;

/**
 * Created by phuongtd on 23/02/2016.
 */
public class CustomerPresenterImpl implements  CustomerPresenter {
    CustomerView mView;

    public CustomerPresenterImpl(CustomerView view){
        mView = view;
    }

    @Override
    public void logout() {
        if(Constant.offLineMode){
            PreferenceUtils.saveToken(mView.getContextBase() , "");
            PreferenceUtils.saveCurrentUserId(mView.getContextBase(), -1);
            mView.onLogoutSuccess();
        } else {
            ServiceBuilder.getService()
                    .logout(PreferenceUtils.getToken(mView.getContextBase())).enqueue(mCallback);
        }
    }

    private BaseCallback mCallback = new BaseCallback<TokenInfoDTO>() {
        @Override
        public void onError(int errorCode, String errorMessage) {
            mView.onRequestError(errorCode, errorMessage);
        }

        @Override
        public void onResponse(Object o) {
            mView.onRequestSuccess();
            mView.onLogoutSuccess();

            PreferenceUtils.saveToken(mView.getContextBase(), "");
            PreferenceUtils.saveCurrentUserId(mView.getContextBase(), -1);
        }
    };
}
