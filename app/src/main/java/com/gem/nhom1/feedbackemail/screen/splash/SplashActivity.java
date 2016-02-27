package com.gem.nhom1.feedbackemail.screen.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gem.nhom1.feedbackemail.network.Session;
import com.gem.nhom1.feedbackemail.network.dto.ResponseUserInfoDTO;
import com.gem.nhom1.feedbackemail.screen.customer.CustomerActivity;
import com.gem.nhom1.feedbackemail.screen.login.LoginActivity;
import com.gem.nhom1.feedbackemail.commom.util.PreferenceUtils;
import com.gem.nhom1.feedbackemail.screen.roleselect.SelectRoleActivity;
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
                    Thread.sleep(2000);
                    detechedAcount();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /*
     * Kiem tra xem co thong tin cua nguoi dung trong phien lam viec truoc ko
     * Neu khong start man hinh login
     */

    public void detechedAcount() {
        ResponseUserInfoDTO userInfoDTO  = PreferenceUtils.getUserInfo(SplashActivity.this);
        if(userInfoDTO!=null){
            Session.setUser(userInfoDTO);

            if(userInfoDTO.getRole().size() > 1){
                Intent intent = new Intent(SplashActivity.this, SelectRoleActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(SplashActivity.this, CustomerActivity.class);
                startActivity(intent);
                finish();
            }

        } else {
            Intent intent = new Intent(SplashActivity.this , LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }


}
