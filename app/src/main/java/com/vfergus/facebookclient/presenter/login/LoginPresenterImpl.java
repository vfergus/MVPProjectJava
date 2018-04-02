package com.vfergus.facebookclient.presenter.login;

import android.support.annotation.NonNull;

import lombok.val;

import com.vfergus.facebookclient.R;
import com.vfergus.facebookclient.core.manager.auth.AuthManager;
import com.vfergus.facebookclient.core.manager.auth.AuthResult;
import com.vfergus.facebookclient.core.manager.message.MessageManager;
import com.vfergus.facebookclient.core.manager.rx.RxManager;
import com.vfergus.facebookclient.presenter.base.BasePresenter;
import com.vfergus.facebookclient.view.LoginScreen;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class LoginPresenterImpl extends BasePresenter<LoginScreen> implements LoginPresenter {

    public LoginPresenterImpl(
        @NonNull final AuthManager authManager,
        @NonNull final MessageManager messageManager,
        @NonNull final RxManager rxManager) {

        mAuthManager = authManager;
        mMessageManager = messageManager;
        mRxManager = rxManager;
    }

    @Override
    public void loginWithFacebook() {
        mDisposables.add(mAuthManager
                            .loginWithFacebook()
                            .subscribeOn(mRxManager.getIOScheduler())
                            .observeOn(mRxManager.getUIScheduler())
                            .subscribeWith(new DisposableObserver<AuthResult>() {
                                @Override
                                public void onNext(final AuthResult authResult) {
                                    displayResult(authResult);
                                }

                                @Override
                                public void onError(final Throwable e) {
                                    mMessageManager.displayMessage(R.string.message_error_auth);
                                }

                                @Override
                                public void onComplete() {

                                }
                            }));
    }

    @Override
    protected void onScreenDetach() {
        super.onScreenDetach();
        if (!mDisposables.isDisposed()) {
            mDisposables.dispose();
        }
    }

    @NonNull
    private final AuthManager mAuthManager;

    @NonNull
    private final CompositeDisposable mDisposables = new CompositeDisposable();

    @NonNull
    private final MessageManager mMessageManager;

    @NonNull
    private final RxManager mRxManager;

    private void displayResult(@NonNull final AuthResult authResult) {
        val screen = getScreen();
        if (screen != null) {
            screen.displaySuccessfully(authResult);
        }
    }
}
