package com.gem.nhom1.feedbackemail.screen.customer;

import com.gem.nhom1.feedbackemail.commom.Constant;
import com.gem.nhom1.feedbackemail.commom.util.DeviceUtils;
import com.gem.nhom1.feedbackemail.commom.util.NetworkUtil;
import com.gem.nhom1.feedbackemail.commom.util.PreferenceUtils;
import com.gem.nhom1.feedbackemail.network.ServiceBuilder;
import com.gem.nhom1.feedbackemail.network.Session;
import com.gem.nhom1.feedbackemail.network.callback.BaseCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        if(NetworkUtil.isNetworkAvaiable(mView.getContextBase())){
            ServiceBuilder.getService()
                    .logout(Session.getCurrentUser().getToken() , DeviceUtils.getDeviceId(mView.getContextBase())).enqueue(mCallback);
        } else{
            mView.onRequestError( Constant.NET_WORK_ERROR);
        }



    }

    private Callback mCallback = new Callback<Object>() {
        @Override
        public void onResponse(Call<Object> call, Response<Object> response) {
            if(response.isSuccess()){
                mView.onLogoutSuccess();
                PreferenceUtils.clearUser(mView.getContextBase());

                Session.removeUser();
            } else {
                mView.onRequestError(response.code() + " , " + response.message());
            }
        }

        @Override
        public void onFailure(Call<Object> call, Throwable t) {
            mView.onRequestError(Constant.CONNECT_TO_SERVER_ERROE);
        }
    };
}
