package com.project.gem.feedbackemail.retrofit;

/**
 * Created by phuongtd on 16/02/2016.
 */
import com.project.gem.feedbackemail.model.ResponseDTO;
import com.project.gem.feedbackemail.util.Constant;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.IOException;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Ashiq Uz Zoha on 9/13/15.
 * Dhrubok Infotech Services Ltd.
 * ashiq.ayon@gmail.com
 */
public class RestClient {

    private static GitApiInterface gitApiInterface ;
    private static String baseUrl = "http://172.16.10.59:8080" ;

    public static GitApiInterface getClient() {
        if (gitApiInterface == null) {

            OkHttpClient okClient = new OkHttpClient();
            okClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            });

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            gitApiInterface = client.create(GitApiInterface.class);
        }
        return gitApiInterface ;
    }

    public interface GitApiInterface {
        @POST("/login")
        Call<ResponseDTO> login(@Query("username") String username , @Query("password") String password) ;


        @GET("/logout")
        Call<ResponseDTO> logout(@Header(Constant.STRING_ACCESS_TOKEN) String access_token);

    }

}
