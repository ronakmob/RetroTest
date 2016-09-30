package com.rx.retro.application;

import android.app.Application;

import com.rx.retro.Constant;
import com.rx.retro.dagger.component.AppComponent;
import com.rx.retro.dagger.component.DaggerAppComponent;
import com.rx.retro.dagger.component.DaggerServiceComponent;
import com.rx.retro.dagger.component.ServiceComponent;
import com.rx.retro.dagger.module.AppModule;
import com.rx.retro.dagger.module.NetworkModule;
import com.rx.retro.dagger.module.ServiceModule;


/**
 * Created by ronakmehta on 9/14/16.
 */
public class RetroApp extends Application {

    AppComponent appComponent;
    ServiceComponent serviceComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
            .appModule(new AppModule(this))
            .networkModule(new NetworkModule(Constant.BASE_URL))
            .build();

        serviceComponent = DaggerServiceComponent.builder().appComponent(appComponent)
            .serviceModule(new ServiceModule()).build();

    }

    public ServiceComponent getServiceComponent() {
        return serviceComponent;
    }
}
