package com.gem.nhom1.feedbackemail.screen.login;

import android.util.Log;

import com.gem.nhom1.feedbackemail.SQLDatabase.CustomerAdapter;
import com.gem.nhom1.feedbackemail.SQLDatabase.DealerAdapter;
import com.gem.nhom1.feedbackemail.SQLDatabase.StaffAdapter;
import com.gem.nhom1.feedbackemail.SQLDatabase.UserAdapter;
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

    private boolean save = false;

    public LoginPresenterImpl(LoginView loginView){
        this.mView = loginView;
    }
    @Override
    public void doLogin(String username, String password , boolean remember) {
        if(validateForm(username , password)==false){

            mView.showError("Input not valid");

            return;
        }

        mView.showProgress();

        Log.d("phuongtd", "Username: " + username.trim());

        save = remember;

        if(NetworkUtil.isNetworkAvaiable(mView.getContextBase())) {

            ServiceBuilder.getService()
                    .login(new UserInfo(username.trim(), password.trim(), DeviceUtils.getDeviceId(mView.getContextBase()))).enqueue(mCallback);
        } else {

            UserAdapter userAdapter = new UserAdapter(mView.getContextBase());
            User user = userAdapter.getUserByUserNamePassWord(username , password);

            if(user == null){
                mView.showError( "Username or password is not valid");
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
        if(username.length() < 6 || passWord.length() < 6){
            return false;
        }
        return true;
    }

    private BaseCallback mCallback = new BaseCallback<TokenInfoDTO>() {
        @Override
        public void onError(int errorCode, String errorMessage) {
            mView.onRequestError(errorCode, errorMessage);
        }

        @Override
        public void onResponse(Object o) {
            TokenInfoDTO tokenInfoDTO = new Gson().fromJson(new Gson().toJson(o) , TokenInfoDTO.class);

            Log.d("phuongtd", "Object 2: " + new Gson().toJson(tokenInfoDTO));

            mView.onRequestSuccess();
            mView.onLoginSuccess(tokenInfoDTO);

            insertUserIntoSQLite(tokenInfoDTO);

            if(save == true){
                PreferenceUtils.saveToken(mView.getContextBase(), tokenInfoDTO.getAccess_token());
                PreferenceUtils.saveCurrentUserId(mView.getContextBase(), tokenInfoDTO.getUser().getUserId());
            }

        }
    };

    private void insertUserIntoSQLite(TokenInfoDTO tokenInfo){

        UserAdapter userAdapter= new UserAdapter(mView.getContextBase());
        int insertUser = (int) userAdapter.insertUser(tokenInfo.getUser());

        if(insertUser == Constant.DUPLICATE_INSERT){
            Log.d("phuongtd", "User da ton tai khong Insert !");
        } else if(insertUser == Constant.INSERT_ERROR){
            Log.d("phuongtd" , "User Insert loi");
        } else {
            Log.d("phuongtd" , "User Insert success");
        }

        if(tokenInfo.getUser().getDealer() != null){
            DealerAdapter dealerAdapter = new DealerAdapter(mView.getContextBase());
            int insertStaff = (int) dealerAdapter.insert(tokenInfo.getUser().getDealer());

            if(insertStaff == Constant.DUPLICATE_INSERT){
                Log.d("phuongtd" , "Dealer da ton tai khong Insert !");
            } else if(insertStaff == Constant.INSERT_ERROR){
                Log.d("phuongtd" , "Dealer Insert loi");
            } else {
                Log.d("phuongtd" , "Dealer Insert success");
            }
        }

        if(tokenInfo.getUser().getCustomer() != null){
            CustomerAdapter customerAdapter = new CustomerAdapter(mView.getContextBase());
            int innsertCustomer = (int) customerAdapter.insert(tokenInfo.getUser().getCustomer());

            if(innsertCustomer == Constant.DUPLICATE_INSERT){
                Log.d("phuongtd" , "Customer da ton tai khong Insert !");
            } else if(innsertCustomer == Constant.INSERT_ERROR){
                Log.d("phuongtd" , "Customer Insert loi");
            } else {
                Log.d("phuongtd" , "Customer Insert success");
            }
        }

        if(tokenInfo.getUser().getStaff() != null){
            StaffAdapter staffAdapter = new StaffAdapter(mView.getContextBase());
            int insertStaff = (int) staffAdapter.insert(tokenInfo.getUser().getStaff());

            if(insertStaff == Constant.DUPLICATE_INSERT){
                Log.d("phuongtd" , "Staff da ton tai khong Insert !");
            } else if(insertStaff == Constant.INSERT_ERROR){
                Log.d("phuongtd" , "Staff Insert loi");
            } else {
                Log.d("phuongtd" , "Staff Insert success");
            }
        }

        User userOffLine = userAdapter.getUserById(tokenInfo.getUser().getUserId());

        Log.d("phuongtd", "User offline: " + new Gson().toJson(userOffLine));
    }
}
