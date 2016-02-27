package com.gem.nhom1.feedbackemail.network;

import com.gem.nhom1.feedbackemail.commom.Constant;
import com.gem.nhom1.feedbackemail.network.dto.ResponseDTO;
import com.gem.nhom1.feedbackemail.network.dto.UserInfoDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by phuongtd on 23/02/2016.
 */
public interface ApiInterface {
    @Headers("Content-Type:application/json")
    @POST("/login")
    Call<ResponseDTO> login(@Body UserInfoDTO user);

    @GET("/logoutuser")
    Call<ResponseDTO> logout(@Header(Constant.TOKEN) String access_token);

    @GET("/store")
    Call<ResponseDTO> getStore(@Header(Constant.TOKEN) String access_token, @Query("page") int page, @Query("size") int size);

    @GET("/store/{id}/product")
    Call<ResponseDTO> getProductByStore(@Header(Constant.TOKEN) String access_token, @Path("id") Long id, @Query("page") int page, @Query("size") int size);
}
