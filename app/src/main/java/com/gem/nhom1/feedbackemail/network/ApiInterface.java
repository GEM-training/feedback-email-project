package com.gem.nhom1.feedbackemail.network;

import com.gem.nhom1.feedbackemail.network.dto.ResponseDTO;
import com.gem.nhom1.feedbackemail.network.entities.UserInfo;
import com.gem.nhom1.feedbackemail.commom.Constant;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by phuongtd on 23/02/2016.
 */
public interface ApiInterface {
    @POST("/login")
    Call<ResponseDTO> login(@Body UserInfo userInfo);

    @GET("/logout")
    Call<ResponseDTO> logout(@Header(Constant.STRING_ACCESS_TOKEN) String access_token);

    @GET("/dealer/{id}")
    Call<ResponseDTO> getInfo(@Header(Constant.STRING_ACCESS_TOKEN) String access_token,@Path("id") int id);



    @GET("dealer/units/{id}")
    Call<ResponseDTO> getListProduct(@Header(Constant.STRING_ACCESS_TOKEN) String access_token, @Path("id") int id);

    @GET("/dealer/list")
    Call<ResponseDTO> getListDealer(@Header(Constant.STRING_ACCESS_TOKEN ) String access_token  , @Query("startIndex") int startIndex , @Query("pageSize") int pageSize);
}
