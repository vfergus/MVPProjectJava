package com.vfergus.facebookclient.di.activity.module;

import android.support.annotation.NonNull;

import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.vfergus.common.view.activity.ObservableActivity;
import com.vfergus.facebookclient.core.manager.facebookAuth.FacebookAuthManager;
import com.vfergus.facebookclient.core.manager.facebookAuth.FacebookAuthManagerImpl;
import com.vfergus.facebookclient.di.activity.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
@ActivityScope
public class FacebookApiModule {

    @Provides
    @ActivityScope
    @NonNull
    public final CallbackManager provideCallbackManager() {
        return CallbackManager.Factory.create();
    }

    @Provides
    @ActivityScope
    @NonNull
    public final FacebookAuthManager provideFacebookManager(
        @NonNull final CallbackManager callbackManager,
        @NonNull final LoginManager loginManager,
        @NonNull final ObservableActivity activity) {

        return new FacebookAuthManagerImpl(callbackManager, loginManager, activity);
    }

    @Provides
    @ActivityScope
    @NonNull
    public final LoginManager provideLoginManager() {
        return LoginManager.getInstance();
    }
}
