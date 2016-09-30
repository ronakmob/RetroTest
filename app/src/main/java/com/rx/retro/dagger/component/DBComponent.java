package com.rx.retro.dagger.component;

import android.content.SharedPreferences;

import com.rx.retro.dagger.module.DatabaseModule;
import com.rx.retro.dagger.scope.UserScope;

import dagger.Component;

/**
 * Created by ronakmehta on 9/29/16.
 */
@UserScope
@Component(dependencies = AppComponent.class,modules = DatabaseModule.class)
public interface DBComponent {

    SharedPreferences preference();

}
