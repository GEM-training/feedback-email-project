package com.project.gem.feedbackemail;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
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
import com.project.gem.feedbackemail.model.Dealer;
import com.project.gem.feedbackemail.model.ResponseDTO;
import com.project.gem.feedbackemail.model.TokenInfo;
import com.project.gem.feedbackemail.retrofit.RestClient;
import com.project.gem.feedbackemail.util.Constant;

import org.w3c.dom.Text;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private AppCompatCheckBox mRememberCb;
    private ProgressBar mProgressView;
    private Button btnLogin;
    private final String ERROR = "Username and password more than 6 character";
    private TextView tvError;
    private final String TOKEN_KEY = "token";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mUsernameView = (AutoCompleteTextView) findViewById(R.id.username);
        mPasswordView = (EditText) findViewById(R.id.password);
        mProgressView = (ProgressBar) findViewById(R.id.login_progress);
        mRememberCb = (AppCompatCheckBox) findViewById(R.id.cb_remember);
        tvError = (TextView) findViewById(R.id.tvError);

        btnLogin = (Button) findViewById(R.id.user_sign_in_button);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("phuongtd" , "mslmvmvlkf");
                String username = mUsernameView.getText().toString();
                String password = mPasswordView.getText().toString();

                Log.d("phuongtd" , "username: " + username);
                if(true){

                    RestClient.GitApiInterface service = RestClient.getClient();
                    Call<ResponseDTO> call = service.login(username.trim(), password.trim());

                    call.enqueue(new Callback<ResponseDTO>() {
                        @Override
                        public void onResponse(Response<ResponseDTO> response) {
                            if(response.isSuccess()){
                                Log.d("phuongtd" , "Status: "+ response.code());

                                ResponseDTO dto = response.body();


                                if(dto.getStatus().equals(Constant.RESPONSE_STATUS_SUSSCESS)){
                                    TokenInfo tokenInfo = new Gson().fromJson(new Gson().toJson(dto.getData()) , TokenInfo.class);

                                    saveToken(tokenInfo.getAccess_token());

                                    Toast.makeText(LoginActivity.this , tokenInfo.getAccess_token() , Toast.LENGTH_LONG).show();

                                    Log.d("phuongtd", "Dealer: " + new Gson().toJson(tokenInfo.getUser().getDealer()));
                                    Log.d("phuongtd", "Customer: " + new Gson().toJson(tokenInfo.getUser().getCustomer()));
                                    Log.d("phuongtd", "Staff: " + new Gson().toJson(tokenInfo.getUser().getStaff()));


                                } else {
                                    String mess = dto.getMessage();
                                    Toast.makeText(LoginActivity.this , mess , Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Log.d("phuongtd" , "Status: "+ response.code());
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            Log.d("phuongtd" , "Fail");
                        }
                    });

                }else{
                    showError();
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

    private void saveToken(String token){
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN_KEY, token);
        editor.commit();
    }

    private void showError(){
        tvError.setVisibility(View.VISIBLE);
        tvError.setText(ERROR);
        tvError.postDelayed(new Runnable() {
            public void run() {
                tvError.setVisibility(View.GONE);
            }
        }, 5000);
    }

    private void login(){

    }
}
