package com.rx.retro.dagger.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.rx.retro.dagger.scope.UserScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ronakmehta on 9/21/16.
 */
@Module
public class DatabaseModule {

    @Provides
    @UserScope
    SharedPreferences providesSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
