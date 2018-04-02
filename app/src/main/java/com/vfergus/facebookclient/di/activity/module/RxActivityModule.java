package com.vfergus.facebookclient.di.activity.module;

import android.support.annotation.NonNull;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.vfergus.facebookclient.core.manager.rx.RxManager;
import com.vfergus.facebookclient.core.manager.rx.RxManagerImpl;
import com.vfergus.facebookclient.di.activity.ActivityScope;
import com.vfergus.facebookclient.di.qualifier.ScopeNames;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
@ActivityScope
public class RxActivityModule {
    public RxActivityModule(
        @NonNull final LifecycleProvider<ActivityEvent> lifecycleProvider) {

        mLifecycleProvider = lifecycleProvider;
    }

    @Named(ScopeNames.ACTIVITY)
    @Provides
    @ActivityScope
    @NonNull
    public final LifecycleProvider<ActivityEvent> provideLifecycleProvider() {
        return mLifecycleProvider;
    }

    @Named(ScopeNames.ACTIVITY)
    @Provides
    @ActivityScope
    @NonNull
    public final RxManager provideRxManager(
        @NonNull @Named(ScopeNames.ACTIVITY)
        final LifecycleProvider<ActivityEvent> lifecycleProvider) {

        return new RxManagerImpl<>(lifecycleProvider);
    }

    @NonNull
    private final LifecycleProvider<ActivityEvent> mLifecycleProvider;
}
