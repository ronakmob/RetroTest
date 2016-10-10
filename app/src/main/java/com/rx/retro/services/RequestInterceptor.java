package com.rx.retro.services;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder modifiedRequest =
            originalRequest.newBuilder();
      /*  modifiedRequest.addHeader(Constant.X_APP_ID, Constant.APP_API_KEY);
        if (originalRequest.header(Constant.CONTENT_TYPE) == null) {
            // Defaulting to contentType :: application/json
            modifiedRequest.addHeader(
                Constant.CONTENT_TYPE, Constant.APPLICATION_JSON);
        }
*/
        Request request = modifiedRequest.build();
        return chain.proceed(request);
    }
}
