package com.vfergus.twitterclient.di;

import android.support.annotation.NonNull;

import com.vfergus.twitterclient.di.activity.ActivityComponent;

public interface ActivityComponentProvider {
    @NonNull
    ActivityComponent getActivityComponent();
}
