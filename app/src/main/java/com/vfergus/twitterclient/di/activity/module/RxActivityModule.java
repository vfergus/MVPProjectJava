package com.vfergus.twitterclient.di.activity.module;

import android.support.annotation.NonNull;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.vfergus.twitterclient.core.manager.rx.RxManagerImpl;
import com.vfergus.twitterclient.core.manager.rx.RxManager;
import com.vfergus.twitterclient.di.qualifier.ScopeNames;
import com.vfergus.twitterclient.di.activity.ActivityScope;

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
