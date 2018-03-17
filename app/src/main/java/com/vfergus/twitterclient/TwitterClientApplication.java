package com.vfergus.twitterclient;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vfergus.twitterclient.di.ApplicationComponentProvider;
import com.vfergus.twitterclient.di.application.ApplicationComponent;
import com.vfergus.twitterclient.di.application.DaggerApplicationComponent;
import com.vfergus.twitterclient.di.application.module.ApiApplicationModule;
import com.vfergus.twitterclient.di.application.module.ManagerApplicationModule;
import com.vfergus.twitterclient.di.application.module.SystemApplicationModule;

import javax.annotation.Nonnull;

public class TwitterClientApplication extends Application implements ApplicationComponentProvider {
    @NonNull
    @Override
    public ApplicationComponent getApplicationComponent() {
        if (_applicationComponent == null) {
            throw new IllegalStateException("The application has not yet been created.");
        }

        return _applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        _applicationComponent = createApplicationComponent();
    }

    @Nullable
    private ApplicationComponent _applicationComponent;

    @Nonnull
    private ApplicationComponent createApplicationComponent() {
        //@formatter:off
        return DaggerApplicationComponent
            .builder()
            .apiApplicationModule(new ApiApplicationModule(true))
            .managerApplicationModule(new ManagerApplicationModule())
            .systemApplicationModule(new SystemApplicationModule(this))
            .build();
        //@formatter:on
    }
}
