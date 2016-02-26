package com.gem.nhom1.feedbackemail.screen.login;

import com.gem.nhom1.feedbackemail.sqlite.CustomerAdapter;
import com.gem.nhom1.feedbackemail.sqlite.DealerAdapter;
import com.gem.nhom1.feedbackemail.sqlite.StaffAdapter;
import com.gem.nhom1.feedbackemail.sqlite.UserAdapter;
import com.gem.nhom1.feedbackemail.commom.util.DeviceUtils;
import com.gem.nhom1.feedbackemail.commom.util.NetworkUtil;
import com.gem.nhom1.feedbackemail.commom.util.PreferenceUtils;
import com.gem.nhom1.feedbackemail.network.ServiceBuilder;
import com.gem.nhom1.feedbackemail.network.callback.BaseCallback;
import com.gem.nhom1.feedbackemail.network.dto.TokenInfoDTO;
import com.gem.nhom1.feedbackemail.network.entities.User;
import com.gem.nhom1.feedbackemail.network.entities.UserInfo;
import com.google.gson.Gson;
import com.gem.nhom1.feedbackemail.commom.Constant;

/**
 * Created by phuongtd on 23/02/2016.
 */
public class LoginPresenterImpl implements LoginPresenter {

    LoginView mView;

    private boolean is_save_account = false;

    private String MESSAGE_INPUT_NOT_VALID = "Input is not valid !";
    private String MESSAGE_LOGIN_NOT_CORRECT = "Username or password not correct !";

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
                    .login(new UserInfo(username.trim(), password.trim(), DeviceUtils.getDeviceId(mView.getContextBase()))).enqueue(mCallback);
        } else {

            UserAdapter userAdapter = new UserAdapter(mView.getContextBase());
            User user = userAdapter.getUserByUserNamePassWord(username , password);

            if(user == null){
                mView.showError(MESSAGE_LOGIN_NOT_CORRECT);
                mView.hideProgress();
            } else {

                mView.onLoginSuccessOffLine(user);
                mView.hideProgress();
                if(remember){
                    PreferenceUtils.saveToken(mView.getContextBase(),"");
                    PreferenceUtils.saveCurrentUserId(mView.getContextBase(), user.getUserId());
                }
            }

        }

    }

    public boolean validateForm(String username, String passWord){
        return !(username.length() < 6 || passWord.length() < 6);
    }

    private BaseCallback mCallback = new BaseCallback<TokenInfoDTO>() {
        @Override
        public void onError(int errorCode, String errorMessage) {
            mView.onRequestError(errorCode, errorMessage);
        }

        @Override
        public void onResponse(Object o) {
            TokenInfoDTO tokenInfoDTO = new Gson().fromJson(new Gson().toJson(o) , TokenInfoDTO.class);

            mView.onRequestSuccess();
            mView.onLoginSuccess(tokenInfoDTO);

            insertUserIntoSQLite(tokenInfoDTO);

            if(is_save_account){
                PreferenceUtils.saveToken(mView.getContextBase(), tokenInfoDTO.getAccess_token());
                PreferenceUtils.saveCurrentUserId(mView.getContextBase(), tokenInfoDTO.getUser().getUserId());
            }

            Constant.CURRENT_ACCESS_TOKEN = tokenInfoDTO.getAccess_token();

        }
    };

    /*
     * Luu lai thong tin nguoi dung vao SQLite
     */

    private void insertUserIntoSQLite(TokenInfoDTO tokenInfo){

        UserAdapter userAdapter= new UserAdapter(mView.getContextBase());
        userAdapter.insertUser(tokenInfo.getUser());

        if(tokenInfo.getUser().getDealer() != null){
            DealerAdapter dealerAdapter = new DealerAdapter(mView.getContextBase());
            dealerAdapter.insert(tokenInfo.getUser().getDealer());
        }

        if(tokenInfo.getUser().getCustomer() != null){
            CustomerAdapter customerAdapter = new CustomerAdapter(mView.getContextBase());
            customerAdapter.insert(tokenInfo.getUser().getCustomer());
        }

        if(tokenInfo.getUser().getStaff() != null){
            StaffAdapter staffAdapter = new StaffAdapter(mView.getContextBase());
            staffAdapter.insert(tokenInfo.getUser().getStaff());
        }

    }
}
