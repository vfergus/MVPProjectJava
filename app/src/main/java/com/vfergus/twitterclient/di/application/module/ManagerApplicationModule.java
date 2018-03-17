package com.vfergus.twitterclient.di.application.module;

import android.content.Context;
import android.support.annotation.NonNull;

import lombok.experimental.Accessors;

import com.vfergus.twitterclient.api.TwitterApi;
import com.vfergus.twitterclient.core.manager.api.ApiManager;
import com.vfergus.twitterclient.core.manager.api.ApiManagerImpl;
import com.vfergus.twitterclient.di.application.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
@ApplicationScope
@Accessors(prefix = "_")
public class ManagerApplicationModule {

    public ManagerApplicationModule() {

    }

    @Provides
    @ApplicationScope
    @NonNull
    public final ApiManager provideApiManager(
        @NonNull final Context context, @NonNull final TwitterApi twitterApi) {

        return new ApiManagerImpl(context, twitterApi);
    }
}
