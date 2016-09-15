package com.rx.retro.sample;

import android.app.Application;

import com.rx.retro.RMRetrofit;

import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ronakmehta on 9/14/16.
 */
public class TestApp extends Application {

    private MyEndpointInterface myEndpointInterface;

    @Override
    public void onCreate() {
        super.onCreate();
        myEndpointInterface = RMRetrofit.getInstance().init(Constant.BASE_URL, GsonConverterFactory.create(), new RequestInterceptor(), MyEndpointInterface.class);
    }

    public MyEndpointInterface getMyEndpointInterface() {
        return myEndpointInterface;
    }
}
