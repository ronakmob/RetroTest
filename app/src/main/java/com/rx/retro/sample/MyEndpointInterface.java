package com.rx.retro.sample;

import com.rx.retro.GitPojo;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface MyEndpointInterface {
    @GET("users/ronakmob")
    Observable<GitPojo> getUser();

    @POST("users/ronakmob")
    Observable<ResponseBody> login(@Body LoginRequest loginRequest);
}
