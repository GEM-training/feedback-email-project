package com.gem.nhom1.feedbackemail.screen.login;

import android.util.Log;

import com.gem.nhom1.feedbackemail.network.Session;
import com.gem.nhom1.feedbackemail.network.dto.ResponseUserInfoDTO;
import com.gem.nhom1.feedbackemail.network.dto.UserInfoDTO;
import com.gem.nhom1.feedbackemail.commom.util.DeviceUtils;
import com.gem.nhom1.feedbackemail.commom.util.NetworkUtil;
import com.gem.nhom1.feedbackemail.commom.util.PreferenceUtils;
import com.gem.nhom1.feedbackemail.network.ServiceBuilder;
import com.gem.nhom1.feedbackemail.network.callback.BaseCallback;
import com.google.gson.Gson;
import com.gem.nhom1.feedbackemail.commom.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                    .login(new UserInfoDTO(username.trim(), password.trim(), DeviceUtils.getDeviceId(mView.getContextBase()) , null)).enqueue(mCallback);
        } else {

           mView.onRequestError(NETWORK_ERROR);

        }

    }

    public boolean validateForm(String username, String passWord){
        return !(username.length() < 5 || passWord.length() < 5);
    }

    private Callback<ResponseUserInfoDTO> mCallback = new Callback<ResponseUserInfoDTO>() {
        @Override
        public void onResponse(Call<ResponseUserInfoDTO> call, Response<ResponseUserInfoDTO> response) {
           if(response.isSuccess()){
               mView.onRequestSuccess();
               ResponseUserInfoDTO  userInfoDTO = response.body();

               Log.d("phuongtd" , "Session User Info" + new Gson().toJson(userInfoDTO));

               mView.onLoginSuccess(userInfoDTO);

               Session.setUser(userInfoDTO);

               if(is_save_account){
                   PreferenceUtils.saveUserInfo(mView.getContextBase() , userInfoDTO);
               }
           } else {
               mView.onRequestError(response.message());
           }
        }

        @Override
        public void onFailure(Call<ResponseUserInfoDTO> call, Throwable t) {
            mView.onRequestError("Connect to Server Error !");
        }
    };

}
