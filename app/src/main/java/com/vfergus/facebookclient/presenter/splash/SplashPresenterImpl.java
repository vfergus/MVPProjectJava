package com.vfergus.facebookclient.presenter.splash;

import android.support.annotation.NonNull;
import android.util.Log;

import lombok.experimental.Accessors;
import lombok.val;

import com.vfergus.facebookclient.core.manager.account.AccountManager;
import com.vfergus.facebookclient.core.manager.account.AppAccount;
import com.vfergus.facebookclient.core.manager.navigation.NavigationManager;
import com.vfergus.facebookclient.core.manager.rx.RxManager;
import com.vfergus.facebookclient.di.qualifier.ScopeNames;
import com.vfergus.facebookclient.presenter.base.BasePresenter;
import com.vfergus.facebookclient.view.Screen;

import javax.inject.Named;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

@Accessors(prefix = "m")
public class SplashPresenterImpl extends BasePresenter<Screen> implements SplashPresenter {

    private static final String _LOG_TAG = SplashPresenterImpl.class.getSimpleName();

    public SplashPresenterImpl(
        @NonNull final AccountManager accountManager,
        @NonNull final NavigationManager navigationManager,
        @NonNull @Named(ScopeNames.ACTIVITY) final RxManager rxManager) {

        mRxManager = rxManager;
        mAccountManager = accountManager;
        mNavigationManager = navigationManager;
    }

    @Override
    public void checkAccounts() {
        val accounts = mAccountManager.getAccounts();
        if (accounts == null || accounts.isEmpty()) {
            mAccountManager
                .addAccount(null)
                .subscribeOn(mRxManager.getIOScheduler())
                .subscribeWith(new DisposableObserver<AppAccount>() {
                    @Override
                    public void onNext(final AppAccount appAccount) {
                        mNavigationManager.navigateToTimeline();
                    }

                    @Override
                    public void onError(final Throwable e) {
                        Log.w(_LOG_TAG, "onError: ", e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        } else {
            mNavigationManager.navigateToTimeline();
        }
    }

    @Override
    protected void onScreenDetach() {
        super.onScreenDetach();
    }

    @NonNull
    private final AccountManager mAccountManager;

    @NonNull
    private final NavigationManager mNavigationManager;

    @NonNull
    private final RxManager mRxManager;

    @NonNull
    private CompositeDisposable mDisposables = new CompositeDisposable();
}
