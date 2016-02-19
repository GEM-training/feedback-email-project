package com.project.gem.feedbackemail.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.project.gem.feedbackemail.R;
import com.project.gem.feedbackemail.SQLDatabase.UserAdapter;
import com.project.gem.feedbackemail.model.ResponseDTO;
import com.project.gem.feedbackemail.model.TokenInfo;
import com.project.gem.feedbackemail.model.UserInfo;
import com.project.gem.feedbackemail.retrofit.RestClient;
import com.project.gem.feedbackemail.util.Constant;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

import android.provider.Settings.Secure;

public class LoginActivity extends AppCompatActivity {
    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private AppCompatCheckBox mRememberCb;
    private ProgressBar mProgressView;
    private Button btnLogin;
    private final String LENGTH_ERROR = "Username and password more than 6 character";
    private final String ERROR_CONNECT = "Can not Connect";
    private TextView tvError;
    private TextView tvRegister;


    private String android_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginRemember();
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        android_id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);

        mUsernameView = (AutoCompleteTextView) findViewById(R.id.username);
        mPasswordView = (EditText) findViewById(R.id.password);
        mProgressView = (ProgressBar) findViewById(R.id.login_progress);
        mRememberCb = (AppCompatCheckBox) findViewById(R.id.cb_remember);
        tvError = (TextView) findViewById(R.id.tvError);
        tvRegister = (TextView) findViewById(R.id.tv_register);
        tvRegister.setMovementMethod(LinkMovementMethod.getInstance());

        btnLogin = (Button) findViewById(R.id.user_sign_in_button);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsernameView.getText().toString();
                String password = mPasswordView.getText().toString();

                Log.d("phuongtd", "username: " + username);

                if (validateForm(username, password)) {
                    login(username, password);

                } else {
                    showError(LENGTH_ERROR);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean validateForm(String username, String passWord){
        if(username.length() < 6 || passWord.length() < 6){
            return false;
        }
        return true;
    }

    private  void saveToken(String token){
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.share_preferences_file),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.TOKEN_KEY, token);
        editor.commit();
    }

    private void saveCurrentUserId(int currentId){
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.share_preferences_file),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constant.CURRENT_USER_ID, currentId);
        editor.commit();
    }


    private void showError(String message){
        tvError.setVisibility(View.VISIBLE);
        tvError.setText(message);
        tvError.postDelayed(new Runnable() {
            public void run() {
                tvError.setVisibility(View.GONE);
            }
        }, 5000);
    }

    private void login(String username, String password){
        RestClient.GitApiInterface service = RestClient.getClient();
        Call<ResponseDTO> call = service.login(new UserInfo(username.trim(),password,android_id));

        call.enqueue(new Callback<ResponseDTO>() {
            @Override
            public void onResponse(Response<ResponseDTO> response) {
                if (response.isSuccess()) {

                    ResponseDTO dto = response.body();

                    if (dto.getStatus().equals("success")) {
                        TokenInfo tokenInfo = new Gson().fromJson(new Gson().toJson(dto.getData()), TokenInfo.class);
                        Toast.makeText(LoginActivity.this, tokenInfo.getAccess_token(), Toast.LENGTH_LONG).show();

                        Constant.MY_TOKEN = tokenInfo.getAccess_token();

                        /*Luu lai userid va access_token cua user hien tai*/

                        saveToken(tokenInfo.getAccess_token());
                        saveCurrentUserId(tokenInfo.getUser().getUserId());


                        /* Luu lai thong tin nguoi dung neu lan dau dang nhap */
                        UserAdapter userAdapter= new UserAdapter(LoginActivity.this);
                        int insert = (int) userAdapter.insertUser(tokenInfo.getUser());

                        if(insert == -2){
                            Log.d("phuongtd" , "User da ton tai khong Insert !");
                        } else if(insert == -1){
                            Log.d("phuongtd" , "Insert loi");
                        } else {
                            Log.d("phuongtd" , "Insert success");
                        }

                        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.share_preferences_file),
                                Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putInt(Constant.USER_ID, tokenInfo.getUser().getIdPersonOfUser());
                        editor.commit();

                        Intent intent =new Intent(LoginActivity.this , MainActivity.class);
                        startActivity(intent);

                        finish();

                        if (mRememberCb.isChecked()) {
                            saveToken(tokenInfo.getAccess_token());
                        }
                    } else {
                        showError(dto.getMessage());
                    }
                } else {
                    Log.d("phuongtd", "Status error: " + response.code());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                showError(ERROR_CONNECT);
            }
        });
    }
    private void loginRemember(){
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.share_preferences_file),
                Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(Constant.TOKEN_KEY, "");
        if(token != ""){
            Constant.MY_TOKEN = token;
            Intent intent =new Intent(LoginActivity.this , MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
