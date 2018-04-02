package com.vfergus.facebookclient.di.fragment.module;

import android.support.annotation.NonNull;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.vfergus.facebookclient.core.manager.rx.RxManager;
import com.vfergus.facebookclient.core.manager.rx.RxManagerImpl;
import com.vfergus.facebookclient.di.fragment.FragmentScope;
import com.vfergus.facebookclient.di.qualifier.ScopeNames;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
@FragmentScope
public class RxFragmentModule {
    public RxFragmentModule(
        @NonNull final LifecycleProvider<FragmentEvent> lifecycleProvider) {

        mLifecycleProvider = lifecycleProvider;
    }

    @Named(ScopeNames.FRAGMENT)
    @Provides
    @FragmentScope
    @NonNull
    public final LifecycleProvider<FragmentEvent> provideLifecycleProvider() {
        return mLifecycleProvider;
    }

    @Named(ScopeNames.FRAGMENT)
    @Provides
    @FragmentScope
    @NonNull
    public final RxManager provideRxManager(
        @NonNull @Named(ScopeNames.FRAGMENT)
        final LifecycleProvider<FragmentEvent> lifecycleProvider) {

        return new RxManagerImpl<>(lifecycleProvider);
    }

    @NonNull
    private final LifecycleProvider<FragmentEvent> mLifecycleProvider;
}
