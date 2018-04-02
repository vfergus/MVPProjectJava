package com.vfergus.facebookclient.core.manager.account;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import lombok.val;

import com.vfergus.facebookclient.di.ApplicationComponentProvider;
import com.vfergus.facebookclient.di.application.ApplicationComponent;

public abstract class BaseService extends Service {

    @NonNull
    public final ApplicationComponent getApplicationComponent() {
        val application = getApplication();
        if (application instanceof ApplicationComponentProvider) {
            final val componentProvider = (ApplicationComponentProvider) application;
            return componentProvider.getApplicationComponent();
        } else {
            throw new IllegalStateException(
                "The application must implement " + ApplicationComponentProvider.class.getName());
        }
    }

    @Nullable
    @Override
    public IBinder onBind(final Intent intent) {
        return null;
    }
}
