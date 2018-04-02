package com.vfergus.facebookclient.di.application.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.vfergus.facebookclient.di.application.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
@ApplicationScope
public class SystemApplicationModule {
    public SystemApplicationModule(
        @NonNull final Context context) {

        mContext = context;
    }

    @Provides
    @ApplicationScope
    @NonNull
    public final Context provideContext() {
        return mContext;
    }

    @NonNull
    private final Context mContext;
}
