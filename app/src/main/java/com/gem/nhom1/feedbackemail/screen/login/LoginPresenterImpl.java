package com.gem.nhom1.feedbackemail.screen.login;

import com.gem.nhom1.feedbackemail.network.dto.ResponseUserInfoDTO;
import com.gem.nhom1.feedbackemail.network.dto.UserInfoDTO;
import com.gem.nhom1.feedbackemail.commom.util.DeviceUtils;
import com.gem.nhom1.feedbackemail.commom.util.NetworkUtil;
import com.gem.nhom1.feedbackemail.commom.util.PreferenceUtils;
import com.gem.nhom1.feedbackemail.network.ServiceBuilder;
import com.gem.nhom1.feedbackemail.network.callback.BaseCallback;
import com.google.gson.Gson;
import com.gem.nhom1.feedbackemail.commom.Constant;

/**
 * Created by phuongtd on 23/02/2016.
 */
public class LoginPresenterImpl implements LoginPresenter {

    LoginView mView;

    private boolean is_save_account = false;

    private final String NETWORK_ERROR = "Can't connect to the Intenet !";
    private final String MESSAGE_INPUT_NOT_VALID = "Input is not valid !";

    public LoginPresenterImpl(LoginView loginView){
        this.mView = loginView;
    }
    @Override
    public void doLogin(String username, String password , boolean remember) {
        if(!validateForm(username, password)){

            mView.showError(MESSAGE_INPUT_NOT_VALID);

            return;
        }

        mView.showProgress();

        is_save_account  = remember;

        if(NetworkUtil.isNetworkAvaiable(mView.getContextBase())) {

            ServiceBuilder.getService()
                    .login(new UserInfoDTO(username.trim(), password.trim(), DeviceUtils.getDeviceId(mView.getContextBase()))).enqueue(mCallback);
        } else {

           mView.onRequestError(1 , NETWORK_ERROR);

        }

    }

    public boolean validateForm(String username, String passWord){
        return !(username.length() < 6 || passWord.length() < 6);
    }

    private BaseCallback mCallback = new BaseCallback<ResponseUserInfoDTO>() {
        @Override
        public void onError(int errorCode, String errorMessage) {
            mView.onRequestError(errorCode, errorMessage);
        }

        @Override
        public void onResponse(Object o) {
            ResponseUserInfoDTO userInfoDTO = new Gson().fromJson(new Gson().toJson(o) , ResponseUserInfoDTO.class);

            mView.onRequestSuccess();
            mView.onLoginSuccess(userInfoDTO);

            if(is_save_account){
               PreferenceUtils.saveUserInfo(mView.getContextBase() , userInfoDTO);
            }
        }
    };

}
