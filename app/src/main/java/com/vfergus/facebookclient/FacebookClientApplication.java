package com.vfergus.facebookclient;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.facebook.appevents.AppEventsLogger;
import com.vfergus.facebookclient.di.ApplicationComponentProvider;
import com.vfergus.facebookclient.di.application.ApplicationComponent;
import com.vfergus.facebookclient.di.application.DaggerApplicationComponent;
import com.vfergus.facebookclient.di.application.module.ApiApplicationModule;
import com.vfergus.facebookclient.di.application.module.ManagerApplicationModule;
import com.vfergus.facebookclient.di.application.module.SystemApplicationModule;

public class FacebookClientApplication extends Application implements ApplicationComponentProvider {

    @NonNull
    @Override
    public ApplicationComponent getApplicationComponent() {
        if (mApplicationComponent == null) {
            throw new IllegalStateException("The application has not yet been created.");
        }

        return mApplicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        AppEventsLogger.activateApp(this);
        mApplicationComponent = createApplicationComponent();
    }

    @Nullable
    private ApplicationComponent mApplicationComponent;

    @NonNull
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
