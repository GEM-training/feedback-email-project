package com.gem.nhom1.feedbackemail.network.callback;


import android.util.Log;

import com.google.gson.Gson;
import com.gem.nhom1.feedbackemail.commom.Constant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by phuongtd on 23/02/2016.
 */
public abstract class BaseCallback<T> implements Callback<T> {
    public static final int NETWORK_ERROR = 999;

    public static final int RESULT_ERROR = 777;

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccess()) {
            // 201 , 200
            T dto = response.body();
            onResponse(dto);

            Log.d("phuongtd" , "response body" + new Gson().toJson(dto));

        } else {
                onError(response.code(), response.message());

        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onError(NETWORK_ERROR, t.getMessage());
    }

    public abstract void onError(int errorCode, String errorMessage);
    public abstract void onResponse(T o);

}