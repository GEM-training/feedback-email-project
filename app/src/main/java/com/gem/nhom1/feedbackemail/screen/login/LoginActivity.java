package com.gem.nhom1.feedbackemail.screen.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gem.nhom1.feedbackemail.base.BaseActivity;
import com.gem.nhom1.feedbackemail.commom.Constant;
import com.gem.nhom1.feedbackemail.commom.util.PreferenceUtils;
import com.gem.nhom1.feedbackemail.network.dto.TokenInfoDTO;
import com.gem.nhom1.feedbackemail.network.entities.User;
import com.gem.nhom1.feedbackemail.screen.customer.CustomerActivity;
import com.gem.nhom1.feedbackemail.screen.dealerDetail.DealerDetailActivity;
import com.gem.nhom1.feedbackemail.screen.staffDetail.StaffDetailActivity;
import com.project.gem.feedbackemail.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by phuongtd on 23/02/2016.
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @Bind(R.id.username)
    AutoCompleteTextView mUsernameView;

    @Bind(R.id.password)
    EditText mPasswordView;

    @Bind(R.id.cb_remember)
    AppCompatCheckBox mRememberCb;

    @Bind(R.id.user_sign_in_button)
    Button btnLogin;

    private final String LENGTH_ERROR = "Username and password more than 6 character";
    private final String ERROR_CONNECT = "Can not Connect";

    @Bind(R.id.tvError)
    TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getPresenter().detechedAcount();
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
    public void onLoginSuccess(TokenInfoDTO tokenInfoDTO) {
        Toast.makeText(this , "Success online" , Toast.LENGTH_LONG).show();

        tvError.setVisibility(View.GONE);

        Constant.offLineMode = false;

        Constant.user = tokenInfoDTO.getUser();

        if(tokenInfoDTO.getUser().getCustomer()!=null){
            Intent intent = new Intent(LoginActivity.this , CustomerActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        if(tokenInfoDTO.getUser().getDealer()!=null){
            Intent intent = new Intent(LoginActivity.this , DealerDetailActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        if(tokenInfoDTO.getUser().getStaff()!=null){
            Intent intent = new Intent(LoginActivity.this , StaffDetailActivity.class);
            startActivity(intent);
            finish();
            return;
        }


    }

    @Override
    public void onLoginSuccessOffLine(User user) {
        Toast.makeText(this , "Success offline" , Toast.LENGTH_LONG).show();


        tvError.setVisibility(View.GONE);

        Constant.offLineMode = true;
        Constant.user = user;

        if(user.getCustomer()!=null){
            Intent intent = new Intent(LoginActivity.this , CustomerActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        if(user.getDealer()!=null){
            Intent intent = new Intent(LoginActivity.this , DealerDetailActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        if(user.getStaff()!=null){
            Intent intent = new Intent(LoginActivity.this , StaffDetailActivity.class);
            startActivity(intent);
            finish();
            return;
        }


    }

    @Override
    public void detechedSuccess(User user) {
        Toast.makeText(this , "Success Deteched" , Toast.LENGTH_LONG).show();

        Constant.offLineMode = true;
        Constant.user = user;


        if(user.getCustomer()!=null){
            Intent intent = new Intent(LoginActivity.this , CustomerActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        if(user.getDealer()!=null){
            Intent intent = new Intent(LoginActivity.this , DealerDetailActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        if(user.getStaff()!=null){
            Intent intent = new Intent(LoginActivity.this , StaffDetailActivity.class);
            startActivity(intent);
            finish();
            return;
        }

    }

    @OnClick(R.id.user_sign_in_button)
    void login(){
        getPresenter().doLogin(mUsernameView.getText().toString() , mPasswordView.getText().toString() , mRememberCb.isChecked());
    }
}
