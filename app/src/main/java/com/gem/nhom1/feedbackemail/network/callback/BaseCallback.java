package com.gem.nhom1.feedbackemail.network.callback;


import com.gem.nhom1.feedbackemail.network.dto.ResponseDTO;
import com.google.gson.Gson;
import com.gem.nhom1.feedbackemail.commom.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by phuongtd on 23/02/2016.
 */
public abstract class BaseCallback<T> implements Callback<ResponseDTO<T>> {
    public static final int NETWORK_ERROR = 999;

    public static final int RESULT_ERROR = 777;

    @Override
    public void onResponse(Call<ResponseDTO<T>> call, Response<ResponseDTO<T>> response) {
        if (response.isSuccess()) {
            // 201 , 200
            ResponseDTO<T> dto = response.body();

            if (dto.getStatus().equals(Constant.RESPONSE_STATUS_SUCCESS)) {
                onResponse(dto.getData());
            } else {
                onError(RESULT_ERROR , dto.getMessage());
            }
        } else {
                onError(response.code() , response.message());
        }
    }

    @Override
    public void onFailure(Call<ResponseDTO<T>> call, Throwable t) {
        onError(NETWORK_ERROR, t.getMessage());
    }

    public abstract void onError(int errorCode, String errorMessage);
    public abstract void onResponse(Object o);

}