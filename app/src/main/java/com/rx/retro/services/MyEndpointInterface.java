package com.rx.retro.services;


import com.rx.retro.request.LoginRequest;
import com.rx.retro.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface MyEndpointInterface<T> {

    @POST("api/1.0/user/login/")
    Observable<UserResponse> loginwithRX(@Body LoginRequest loginRequest);

    @POST("api/1.0/user/login/")
    Call<UserResponse> loginWithRetrofti(@Body LoginRequest loginRequest);
}
