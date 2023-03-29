package com.example.ultimate_sweat_buddies.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;
    private static final String BASEURL = "https://api.usmanfarooqi.xyz";


    public static Retrofit getRetrofit() {
        if(retrofit == null){
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new Authentication("mobiledev", "mobiledev123*"))
                    .build();
            retrofit = new Retrofit.Builder().
                    baseUrl(BASEURL).
                    addConverterFactory(GsonConverterFactory.create()).
                    client(client).
                    build();
        }
        return retrofit;
    }
}
