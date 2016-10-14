package com.rx.retro.dagger.component;

import com.rx.retro.dagger.module.ServiceModule;
import com.rx.retro.dagger.scope.UserScope;
import com.rx.retro.viewModel.activity.BaseActivity;

import dagger.Component;

/**
 * Created by ronakmehta on 9/27/16.
 */

@UserScope
@Component(dependencies = AppComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

    void inject(BaseActivity baseActivity);
}
