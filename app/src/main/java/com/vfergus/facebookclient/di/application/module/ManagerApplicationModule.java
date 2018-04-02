package com.vfergus.facebookclient.di.application.module;

import android.accounts.AccountManager;
import android.content.Context;
import android.support.annotation.NonNull;

import lombok.experimental.Accessors;

import com.vfergus.facebookclient.api.FacebookClientApi;
import com.vfergus.facebookclient.core.manager.api.ApiManager;
import com.vfergus.facebookclient.core.manager.api.ApiManagerImpl;
import com.vfergus.facebookclient.di.application.ApplicationScope;

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
        @NonNull final Context context, @NonNull final FacebookClientApi facebookClientApi) {

        return new ApiManagerImpl(context, facebookClientApi);
    }

    @Provides
    @ApplicationScope
    @NonNull
    public final android.accounts.AccountManager provideNativeAccountManager(
        @NonNull final Context context) {

        return AccountManager.get(context);
    }
}
