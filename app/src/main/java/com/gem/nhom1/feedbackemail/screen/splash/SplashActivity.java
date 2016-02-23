package com.gem.nhom1.feedbackemail.screen.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.gem.nhom1.feedbackemail.SQLDatabase.UserAdapter;
import com.gem.nhom1.feedbackemail.commom.Constant;
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

    public void detechedAcount() {
        if(PreferenceUtils.getToken(SplashActivity.this)!="" && PreferenceUtils.getCurrentUserId(SplashActivity.this)!=-1){
            UserAdapter userAdapter = new UserAdapter(SplashActivity.this);

            User user = userAdapter.getUserById(PreferenceUtils.getCurrentUserId(SplashActivity.this));

            if(user != null){
                Constant.offLineMode = true;
                Constant.user = user;

                Toast.makeText(SplashActivity.this , "Deteched Success" , Toast.LENGTH_SHORT).show();

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
        Log.d("phuongtd" , "Start Login");
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();

    }


}
