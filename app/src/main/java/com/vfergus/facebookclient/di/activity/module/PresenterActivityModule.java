package com.vfergus.facebookclient.di.activity.module;

import android.support.annotation.NonNull;
import android.util.Log;

import com.vfergus.facebookclient.core.manager.account.AccountHelper;
import com.vfergus.facebookclient.core.manager.account.AccountManager;
import com.vfergus.facebookclient.core.manager.api.ApiManager;
import com.vfergus.facebookclient.core.manager.auth.AuthManager;
import com.vfergus.facebookclient.core.manager.message.MessageManager;
import com.vfergus.facebookclient.core.manager.navigation.NavigationManager;
import com.vfergus.facebookclient.core.manager.rx.RxManager;
import com.vfergus.facebookclient.di.activity.ActivityScope;
import com.vfergus.facebookclient.di.qualifier.PresenterNames;
import com.vfergus.facebookclient.di.qualifier.ScopeNames;
import com.vfergus.facebookclient.presenter.login.LoginPresenter;
import com.vfergus.facebookclient.presenter.login.LoginPresenterImpl;
import com.vfergus.facebookclient.presenter.splash.SplashPresenter;
import com.vfergus.facebookclient.presenter.splash.SplashPresenterImpl;
import com.vfergus.facebookclient.presenter.userPosts.PostsPresenter;
import com.vfergus.facebookclient.presenter.userPosts.PostsPresenterImpl;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
@ActivityScope
public class PresenterActivityModule {

    private static final String LOG_TAG = PresenterActivityModule.class.getSimpleName();

    @Provides
    @Named(PresenterNames.LOGIN)
    @NonNull
    @ActivityScope
    public LoginPresenter provideLoginPresenter(
        @NonNull final AuthManager authManager,
        @NonNull final MessageManager messageManager,
        @NonNull @Named(ScopeNames.ACTIVITY) final RxManager rxManager) {

        return new LoginPresenterImpl(authManager, messageManager, rxManager);
    }

    @Provides
    @Named(PresenterNames.SPLASH)
    @NonNull
    @ActivityScope
    public SplashPresenter provideSplashPresenter(
        @NonNull final AccountManager accountManager,
        @NonNull final NavigationManager navigationManager,
        @NonNull @Named(ScopeNames.ACTIVITY) final RxManager rxManager) {

        Log.d(LOG_TAG, "provideSplashPresenter:  = ");
        return new SplashPresenterImpl(accountManager, navigationManager, rxManager);
    }

    @Provides
    @Named(PresenterNames.POST_LIST)
    @NonNull
    @ActivityScope
    public PostsPresenter provideTimelinePresenter(
        @NonNull final AccountHelper accountHelper,
        @NonNull final NavigationManager navigationManager,
        @NonNull final ApiManager apiManager,
        @NonNull final MessageManager messageManager,
        @NonNull @Named(ScopeNames.ACTIVITY) final RxManager rxManager) {

        return new PostsPresenterImpl(accountHelper,
                                      navigationManager,
                                      apiManager,
                                      messageManager,
                                      rxManager);
    }
}
