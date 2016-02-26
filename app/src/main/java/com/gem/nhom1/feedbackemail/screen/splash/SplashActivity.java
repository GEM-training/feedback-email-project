package com.gem.nhom1.feedbackemail.screen.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gem.nhom1.feedbackemail.sqlite.UserAdapter;
import com.gem.nhom1.feedbackemail.commom.Constant;
import com.gem.nhom1.feedbackemail.commom.util.NetworkUtil;
import com.gem.nhom1.feedbackemail.commom.util.PreferenceUtils;
import com.gem.nhom1.feedbackemail.network.entities.User;
import com.gem.nhom1.feedbackemail.screen.customer.CustomerActivity;
import com.gem.nhom1.feedbackemail.screen.dealerDetail.DealerDetailActivity;
import com.gem.nhom1.feedbackemail.screen.login.LoginActivity;
import com.gem.nhom1.feedbackemail.screen.staffDetail.StaffDetailActivity;
import com.project.gem.feedbackemail.R;

/**
 * Created by phuong on 2/23/2016.
 */
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_activity);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    detechedAcount();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /*
     * Kiem tra xem co thong tin cua nguoi dung trong phien lam viec truoc ki
     * Neu co start man hinh profile
     * Neu khong start man hinh login
     */

    public void detechedAcount() {
        if(PreferenceUtils.getToken(SplashActivity.this)!= PreferenceUtils.TOKEN_EMPTY && PreferenceUtils.getCurrentUserId(SplashActivity.this)!=PreferenceUtils.USER_ID_EMPTY){
            UserAdapter userAdapter = new UserAdapter(SplashActivity.this);

            User user = userAdapter.getUserById(PreferenceUtils.getCurrentUserId(SplashActivity.this));

            if(user != null){
                if(NetworkUtil.isNetworkAvaiable(SplashActivity.this))
                {
                    Constant.offLineMode = false;
                } else {
                    Constant.offLineMode = true;
                }
                Constant.user = user;
                Constant.CURRENT_ACCESS_TOKEN = PreferenceUtils.getToken(SplashActivity.this);

                if(user.getCustomer()!=null){
                    Intent intent = new Intent(SplashActivity.this , CustomerActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }

                if(user.getDealer()!=null){
                    Intent intent = new Intent(SplashActivity.this , DealerDetailActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }

                if(user.getStaff()!=null){
                    Intent intent = new Intent(SplashActivity.this , StaffDetailActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }

        }
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();

    }


}
