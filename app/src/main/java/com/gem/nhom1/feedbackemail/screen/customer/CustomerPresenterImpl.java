package com.gem.nhom1.feedbackemail.screen.customer;

import com.gem.nhom1.feedbackemail.commom.Constant;
import com.gem.nhom1.feedbackemail.commom.util.NetworkUtil;
import com.gem.nhom1.feedbackemail.commom.util.PreferenceUtils;
import com.gem.nhom1.feedbackemail.network.ServiceBuilder;
import com.gem.nhom1.feedbackemail.network.Session;
import com.gem.nhom1.feedbackemail.network.callback.BaseCallback;

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
                    .logout(Session.getCurrentUser().getToken()).enqueue(mCallback);
        } else{
            mView.onRequestError( Constant.NET_WORK_ERROR);
        }



    }

    private BaseCallback mCallback = new BaseCallback<Object>() {
        @Override
        public void onError(int errorCode, String errorMessage) {
            mView.onRequestError(errorMessage);
        }

        @Override
        public void onResponse(Object o) {
            mView.onRequestSuccess();
            mView.onLogoutSuccess();

            Session.removeUser();

            PreferenceUtils.clearUser(mView.getContextBase());

            mView.onLogoutSuccess();
        }
    };
}
