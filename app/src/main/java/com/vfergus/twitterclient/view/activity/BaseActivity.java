package com.vfergus.twitterclient.view.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import lombok.val;

import com.vfergus.common.view.activity.ExtendedActivity;
import com.vfergus.twitterclient.di.ActivityComponentProvider;
import com.vfergus.twitterclient.di.ApplicationComponentProvider;
import com.vfergus.twitterclient.di.activity.ActivityComponent;
import com.vfergus.twitterclient.di.activity.module.ManagerActivityModule;
import com.vfergus.twitterclient.di.activity.module.PresenterActivityModule;
import com.vfergus.twitterclient.di.activity.module.RxActivityModule;
import com.vfergus.twitterclient.di.activity.module.SystemActivityModule;
import com.vfergus.twitterclient.di.application.ApplicationComponent;

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
        @Nullable final Bundle savedInstanceState,
        @Nullable final PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        _activityComponent = onCreateActivityComponent();
    }

    @NonNull
    protected ActivityComponent onCreateActivityComponent() {
        //@formatter:off
        return getApplicationComponent().addActivityComponent(
                new SystemActivityModule(this),
                new RxActivityModule(this),
                new PresenterActivityModule(),
                new ManagerActivityModule());
        //@formatter:on
    }

    @Nullable
    private ActivityComponent _activityComponent;
}
