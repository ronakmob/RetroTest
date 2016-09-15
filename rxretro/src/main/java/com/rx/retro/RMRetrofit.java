package com.rx.retro;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by ronakmehta on 9/15/16.
 */
public class RMRetrofit {
    private static RMRetrofit instance = null;
    private static Retrofit retrofit;

    public static RMRetrofit getInstance() {
        if (instance == null) {
            synchronized (RMRetrofit.class) {
                if (instance == null) {
                    instance = new RMRetrofit();
                }
            }
        }
        return instance;
    }

    public <S> S init(String baseUrl, Converter.Factory factory, Interceptor interceptor, Class<S> serviceClass) {
        OkHttpClient client;
        if (interceptor == null) {
            client = new OkHttpClient();
        } else {
            client = new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor).build();
        }

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .client(client)
                .build();
        }
        return retrofit.create(serviceClass);
    }

    private <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
