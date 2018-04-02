package com.vfergus.facebookclient.di.activity.module;

import android.support.annotation.NonNull;

import com.vfergus.common.view.activity.ObservableActivity;
import com.vfergus.facebookclient.di.activity.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
@ActivityScope
public class SystemActivityModule {
    public SystemActivityModule(@NonNull final ObservableActivity observableActivity) {

        _observableActivity = observableActivity;
    }

    @Provides
    @ActivityScope
    @NonNull
    public final ObservableActivity provideObservableActivity() {
        return _observableActivity;
    }

    @NonNull
    private final ObservableActivity _observableActivity;
}
