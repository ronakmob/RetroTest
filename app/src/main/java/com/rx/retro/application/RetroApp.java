package com.rx.retro.application;

import android.app.Application;
import android.content.Context;

import com.rx.retro.Constant;
import com.rx.retro.dagger.component.AppComponent;
import com.rx.retro.dagger.component.DaggerAppComponent;
import com.rx.retro.dagger.component.DaggerServiceComponent;
import com.rx.retro.dagger.component.ServiceComponent;
import com.rx.retro.dagger.module.AppModule;
import com.rx.retro.dagger.module.NetworkModule;
import com.rx.retro.dagger.module.ServiceModule;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;


/**
 * Created by ronakmehta on 9/14/16.
 */
public class RetroApp extends Application {

    AppComponent appComponent;
    ServiceComponent serviceComponent;
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
            .appModule(new AppModule(this))
            .networkModule(new NetworkModule(Constant.BASE_URL))
            .build();

        serviceComponent = DaggerServiceComponent.builder().appComponent(appComponent)
            .serviceModule(new ServiceModule()).build();

        configLeakCanarry();

    }

    private void configLeakCanarry() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher = LeakCanary.install(this);
    }

    public RefWatcher getRefWatcher() {
        return refWatcher;
    }

    public ServiceComponent getServiceComponent() {
        return serviceComponent;
    }

    public static RefWatcher getRefWatcher(Context context) {
        RetroApp application = (RetroApp) context.getApplicationContext();
        return application.refWatcher;
    }
}
