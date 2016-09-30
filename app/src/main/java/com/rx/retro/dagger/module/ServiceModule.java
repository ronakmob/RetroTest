package com.rx.retro.dagger.module;

import com.rx.retro.dagger.scope.UserScope;
import com.rx.retro.services.MyEndpointInterface;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by ronakmehta on 9/27/16.
 */
@Module
public class ServiceModule {

    @UserScope
    @Provides
    public MyEndpointInterface provideService(Retrofit retrofit) {
        return retrofit.create(MyEndpointInterface.class);
    }


}
