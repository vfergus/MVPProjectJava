package com.vfergus.twitterclient.di.activity.module;

import android.support.annotation.NonNull;

import com.vfergus.common.manager.permission.ActivityPermissionManager;
import com.vfergus.common.manager.permission.PermissionManager;
import com.vfergus.common.view.activity.ObservableActivity;
import com.vfergus.twitterclient.R;
import com.vfergus.twitterclient.core.manager.message.MessageManager;
import com.vfergus.twitterclient.core.manager.message.MessageManagerImpl;
import com.vfergus.twitterclient.core.manager.navigation.NavigationManager;
import com.vfergus.twitterclient.core.manager.navigation.NavigationManagerImpl;
import com.vfergus.twitterclient.di.activity.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
@ActivityScope
public class ManagerActivityModule {

    @Provides
    @ActivityScope
    @NonNull
    public final MessageManager provideMessageManager(
        @NonNull final ObservableActivity observableActivity) {

        return new MessageManagerImpl(observableActivity);
    }

    @Provides
    @ActivityScope
    @NonNull
    public final NavigationManager provideNavigationManager(
        @NonNull final ObservableActivity observableActivity) {

        return new NavigationManagerImpl(observableActivity, R.id.container);
    }

    @Provides
    @ActivityScope
    @NonNull
    public final PermissionManager providePermissionManager(
        @NonNull final ObservableActivity observableActivity) {

        return new ActivityPermissionManager(observableActivity);
    }
}
