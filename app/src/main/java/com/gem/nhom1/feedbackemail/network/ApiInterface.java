package com.gem.nhom1.feedbackemail.network;

import com.gem.nhom1.feedbackemail.commom.Constant;
import com.gem.nhom1.feedbackemail.network.dto.ListProductDTO;
import com.gem.nhom1.feedbackemail.network.dto.ListStoreDTO;
import com.gem.nhom1.feedbackemail.network.dto.ResponseUserInfoDTO;
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
    @POST("/authenticate/login")
    Call<ResponseUserInfoDTO> login(@Body UserInfoDTO user);

    @POST("/authenticate/logout")
    Call<Void> logout(@Header(Constant.TOKEN) String access_token , @Header("deviceId") String deviceId);

    @GET("/store")
    Call<ListStoreDTO> getStore(@Header(Constant.TOKEN) String access_token, @Header("deviceId") String deviceId,
                                @Query("page") int page , @Query("size") int size);

    @GET("/store/{id}/product")
    Call<ListProductDTO> getProductByStore(@Header(Constant.TOKEN) String access_token,
                                           @Header("deviceId") String deviceId , @Path("id") Long id,
                                           @Query("from") String from, @Query("to") String to,
                                           @Query("page") int page, @Query("size") int size);
}
