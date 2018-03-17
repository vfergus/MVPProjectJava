package com.vfergus.twitterclient.core.manager.navigation;

import android.support.annotation.IdRes;

import com.vfergus.common.view.activity.ObservableActivity;

import javax.annotation.Nonnull;

public class NavigationManagerImpl implements NavigationManager {
    public NavigationManagerImpl(
        @Nonnull final ObservableActivity observableActivity, final int containerId) {

        mObservableActivity = observableActivity;
        mContainerId = containerId;
    }

    @IdRes
    private final int mContainerId;

    @Nonnull
    private final ObservableActivity mObservableActivity;
}
