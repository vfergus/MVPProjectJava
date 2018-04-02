package com.vfergus.facebookclient.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import lombok.val;

import com.vfergus.common.view.activity.ExtendedActivity;
import com.vfergus.facebookclient.di.ActivityComponentProvider;
import com.vfergus.facebookclient.di.ApplicationComponentProvider;
import com.vfergus.facebookclient.di.activity.ActivityComponent;
import com.vfergus.facebookclient.di.activity.module.FacebookApiModule;
import com.vfergus.facebookclient.di.activity.module.ManagerActivityModule;
import com.vfergus.facebookclient.di.activity.module.PresenterActivityModule;
import com.vfergus.facebookclient.di.activity.module.RxActivityModule;
import com.vfergus.facebookclient.di.activity.module.SystemActivityModule;
import com.vfergus.facebookclient.di.application.ApplicationComponent;

public abstract class BaseActivity extends ExtendedActivity implements ActivityComponentProvider {

    @Override
    @NonNull
    public final ActivityComponent getActivityComponent() {
        if (_activityComponent == null) {
            throw new IllegalStateException("The activity has not yet been created.");
        }

        return _activityComponent;
    }

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

    @Override
    public void onCreate(
        @Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _activityComponent = onCreateActivityComponent();
    }

    @NonNull
    protected ActivityComponent onCreateActivityComponent() {
        //@formatter:off
        return getApplicationComponent().addActivityComponent(
                new SystemActivityModule(this),
                new RxActivityModule(this),
                new PresenterActivityModule(),
                new ManagerActivityModule(),
                new FacebookApiModule());
        //@formatter:on
    }

    @Nullable
    private ActivityComponent _activityComponent;
}
