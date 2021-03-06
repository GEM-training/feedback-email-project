package com.gem.nhom1.feedbackemail.network;

import okhttp3.OkHttpClient;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by phuongtd on 23/02/2016.
 */
public class ServiceBuilder {
    private static final String BASE_URL = "http://172.16.10.69:8080";

    private static Retrofit sInstance;
    private static ApiInterface sService;

    private static Retrofit getRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if (sInstance == null) {
            sInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return sInstance;
    }

    public static ApiInterface getService() {
        if (sService == null) {
            sService = getRetrofit().create(ApiInterface.class);
        }

        return sService;
    }
}