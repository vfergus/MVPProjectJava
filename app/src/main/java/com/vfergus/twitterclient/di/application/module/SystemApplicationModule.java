package com.vfergus.twitterclient.di.application.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.vfergus.twitterclient.di.application.ApplicationScope;

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
