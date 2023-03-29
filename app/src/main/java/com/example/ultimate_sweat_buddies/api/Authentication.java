package com.example.ultimate_sweat_buddies.api;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class Authentication implements Interceptor {
    private String credentials;
    public Authentication(String user, String password){
        this.credentials = Credentials.basic(user, password);
    }
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Request authentication = request.newBuilder()
                .header("Authorization", credentials).build();
        return chain.proceed(authentication);
    }
}

