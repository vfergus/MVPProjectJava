package com.vfergus.facebookclient.di;

import android.support.annotation.NonNull;

import com.vfergus.facebookclient.di.activity.ActivityComponent;

public interface ActivityComponentProvider {
    @NonNull
    ActivityComponent getActivityComponent();
}
