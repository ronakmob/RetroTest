package com.rx.retro.dagger.component;

import android.content.Context;

import com.rx.retro.dagger.module.AppModule;
import com.rx.retro.dagger.module.NetworkModule;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {
    AppModule.class,
    NetworkModule.class
})
public interface AppComponent {

    Context context();

    Retrofit retrofit();

    EventBus eventBus();
}
