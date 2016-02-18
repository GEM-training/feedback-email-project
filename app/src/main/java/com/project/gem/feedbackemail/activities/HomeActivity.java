package com.project.gem.feedbackemail.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.project.gem.feedbackemail.R;
import com.project.gem.feedbackemail.model.ResponseDTO;
import com.project.gem.feedbackemail.retrofit.RestClient;
import com.project.gem.feedbackemail.util.Constant;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by phuongtd on 18/02/2016.
 */
public class HomeActivity extends Activity {

    private Button btLogout;

    private TextView tvUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        btLogout = (Button) findViewById(R.id.bt_logout);

        tvUsername = (TextView) findViewById(R.id.tv_username);

        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        Intent intent = getIntent();
        Bundle  bundle = intent.getExtras();

        tvUsername.setText(bundle.get("username").toString());
    }

    public void logout(){
        RestClient.GitApiInterface service = RestClient.getClient();
        Call<ResponseDTO> call = service.logout(Constant.MY_TOKEN);

        call.enqueue(new Callback<ResponseDTO>() {
            @Override
            public void onResponse(Response<ResponseDTO> response) {

                Log.d("phuongtd" , "Status logout: " + response.code());

                if(response.isSuccess()){
                    ResponseDTO dto = response.body();

                    if(dto.getStatus().equals(Constant.RESPONSE_STATUS_SUSSCESS)){
                        Toast.makeText(HomeActivity.this , "Logout Success", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(HomeActivity.this , LoginActivity.class);


                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(HomeActivity.this , "Khong the logout tren Server", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(HomeActivity.this , response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
