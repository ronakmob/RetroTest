package com.rx.retro;

import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ronakmehta on 9/6/16.
 */
public abstract class ApiManager<T> {
    private static final String TAG = ApiManager.class.getName();

    public abstract void onSuccess(Response<T> response);

    public void onFail(Response<T> response){}

    public ApiManager(Call<T> call) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response != null && response.isSuccessful() && response.body() != null) {
                    onSuccess(response);
                } else {
                    onFail(response);
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if (t instanceof IOException) {
                    Log.e(TAG, "onFailure: Check connection");
                } else {
                    Log.e(TAG, "onFailure: Other ERROR");
                }
            }
        });
    }
}
