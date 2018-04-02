package com.vfergus.facebookclient.di;

import android.support.annotation.NonNull;

import com.vfergus.facebookclient.di.application.ApplicationComponent;

public interface ApplicationComponentProvider {
    @NonNull
    ApplicationComponent getApplicationComponent();
}
