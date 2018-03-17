package com.vfergus.twitterclient.di;

import android.support.annotation.NonNull;

import com.vfergus.twitterclient.di.application.ApplicationComponent;

public interface ApplicationComponentProvider {
    @NonNull
    ApplicationComponent getApplicationComponent();
}
