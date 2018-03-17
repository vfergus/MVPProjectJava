package com.vfergus.twitterclient.di.fragment.module;

import android.support.annotation.NonNull;

import com.vfergus.common.view.fragment.ObservableFragment;
import com.vfergus.twitterclient.di.fragment.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
@FragmentScope
public class SystemFragmentModule {
    public SystemFragmentModule(@NonNull final ObservableFragment observableFragment) {

        mObservableFragment = observableFragment;
    }

    @Provides
    @FragmentScope
    @NonNull
    public final ObservableFragment provideObservableFragment() {
        return mObservableFragment;
    }

    @NonNull
    private final ObservableFragment mObservableFragment;
}
