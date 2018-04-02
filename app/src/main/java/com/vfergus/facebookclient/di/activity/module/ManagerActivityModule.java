package com.vfergus.facebookclient.di.activity.module;

import android.accounts.AccountManager;
import android.support.annotation.NonNull;

import com.vfergus.common.view.activity.ObservableActivity;
import com.vfergus.facebookclient.core.manager.account.AccountConstants;
import com.vfergus.facebookclient.core.manager.account.AccountHelper;
import com.vfergus.facebookclient.core.manager.account.AccountHelperImpl;
import com.vfergus.facebookclient.core.manager.account.AccountManagerImpl;
import com.vfergus.facebookclient.core.manager.auth.AuthManager;
import com.vfergus.facebookclient.core.manager.auth.AuthManagerImpl;
import com.vfergus.facebookclient.core.manager.facebookAuth.FacebookAuthManager;
import com.vfergus.facebookclient.core.manager.message.MessageManager;
import com.vfergus.facebookclient.core.manager.message.MessageManagerImpl;
import com.vfergus.facebookclient.core.manager.navigation.NavigationManager;
import com.vfergus.facebookclient.core.manager.navigation.NavigationManagerImpl;
import com.vfergus.facebookclient.di.activity.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
@ActivityScope
public class ManagerActivityModule {

    @Provides
    @ActivityScope
    @NonNull
    public final AccountHelper provideAccountHelper(
        @NonNull
        final com.vfergus.facebookclient.core.manager.account.AccountManager accountManager) {

        return new AccountHelperImpl(accountManager);
    }

    @Provides
    @ActivityScope
    @NonNull
    public final com.vfergus.facebookclient.core.manager.account.AccountManager
    provideAccountManager(
        @NonNull final android.accounts.AccountManager nativeAccountManager,
        @NonNull final ObservableActivity observableActivity) {

        return new AccountManagerImpl(
            AccountConstants.ACCOUNT_TYPE,
            nativeAccountManager,
            observableActivity);
    }

    @Provides
    @ActivityScope
    @NonNull
    public final AuthManager provideAuthManager(
        @NonNull final AccountManager accountManager,
        @NonNull final FacebookAuthManager facebookAuthManager) {

        return new AuthManagerImpl(facebookAuthManager, accountManager);
    }

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

        return new NavigationManagerImpl(observableActivity);
    }
}
