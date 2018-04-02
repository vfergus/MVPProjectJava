package com.vfergus.facebookclient.view.activity;

import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import lombok.experimental.Accessors;
import lombok.val;

import butterknife.OnClick;

import com.vfergus.facebookclient.R;
import com.vfergus.facebookclient.core.manager.auth.AuthResult;
import com.vfergus.facebookclient.di.qualifier.PresenterNames;
import com.vfergus.facebookclient.presenter.login.LoginPresenter;
import com.vfergus.facebookclient.view.LoginScreen;

import javax.inject.Inject;
import javax.inject.Named;

@Accessors(prefix = "m")
public class LoginActivity extends BaseVerificationActivity implements LoginScreen {

    private static final String _KEY_ACCOUNT_TYPE = "key_account_type";

    @NonNull
    public static Intent getIntent(
        @NonNull final Context context, @NonNull final String accountType) {

        val intent = new Intent(context, LoginActivity.class);
        intent.putExtra(_KEY_ACCOUNT_TYPE, accountType);

        return intent;
    }

    @Override
    public void displaySuccessfully(@NonNull final AuthResult authResult) {
        setAccountAuthenticatorResult(createResult(authResult));
        finish();
    }

    @Override
    public void onBackPressed() {
        if (getAccountAuthenticatorResult() != null) {
            setResult(RESULT_OK);
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @CallSuper
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivityComponent().inject(this);
        if (mPresenter != null) {
            mPresenter.attachScreen(this);
        }
        setContentView(R.layout.activity_login);
        bindViews();
    }

    @NonNull
    protected Bundle createResult(@NonNull final AuthResult authResult) {
        val userInfo = new Bundle(2);
        userInfo.putString(AccountManager.KEY_ACCOUNT_NAME, authResult.getUserName());
        userInfo.putString(AccountManager.KEY_ACCOUNT_TYPE, authResult.getAccountType());

        return userInfo;
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachScreen();
        }
        super.onDestroy();
    }

    @Named(PresenterNames.LOGIN)
    @Inject
    @Nullable
    LoginPresenter mPresenter;

    @OnClick(R.id.login)
    void onClick() {
        if (mPresenter != null) {
            mPresenter.loginWithFacebook();
        }
    }
}
