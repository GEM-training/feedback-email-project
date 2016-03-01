package com.gem.nhom1.feedbackemail.screen.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gem.nhom1.feedbackemail.base.BaseActivity;
import com.gem.nhom1.feedbackemail.network.dto.ResponseUserInfoDTO;
import com.gem.nhom1.feedbackemail.screen.customer.CustomerActivity;
import com.gem.nhom1.feedbackemail.screen.roleselect.SelectRoleActivity;
import com.project.gem.feedbackemail.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by phuongtd on 23/02/2016.
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @Bind(R.id.username)
    EditText mUsernameView;

    @Bind(R.id.password)
    EditText mPasswordView;

    @Bind(R.id.user_sign_in_button)
    Button btnLogin;

   /* @Bind(R.id.toolbar)
    Toolbar toolbar;*/

    @Bind(R.id.tvError)
    TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setSupportActionBar(toolbar);
        //getPresenter().detechedAcount();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginPresenter onCreatePresenter() {
        return new LoginPresenterImpl(this);
    }

    @Override
    public Context getContextBase() {
        return this;
    }

    @Override
    public void showError(String mess) {
        tvError.setVisibility(View.VISIBLE);
        tvError.setText(mess);
    }

    @Override
    public void onLoginSuccess(ResponseUserInfoDTO userInfoDTO) {
        tvError.setVisibility(View.GONE);
        /*if(userInfoDTO.getRoles().size() > 1){
            Intent intent = new Intent(LoginActivity.this, SelectRoleActivity.class);
            startActivity(intent);
            finish();
        } else {*/
            Intent intent = new Intent(LoginActivity.this, CustomerActivity.class);
            startActivity(intent);
            finish();
        // }

    }

    @OnClick(R.id.user_sign_in_button)
    void login(){
        getPresenter().doLogin(mUsernameView.getText().toString() , mPasswordView.getText().toString() , true);
    }
}
