package com.vfergus.facebookclient.view.activity;

import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.val;

import com.vfergus.facebookclient.R;

@Accessors(prefix = "m")
public abstract class BaseVerificationActivity extends BaseActivity {

    @CallSuper
    @Override
    public void finish() {
        if (mAccountAuthenticatorResponse != null) {
            val accountAuthenticatorResult = getAccountAuthenticatorResult();
            if (accountAuthenticatorResult != null) {
                mAccountAuthenticatorResponse.onResult(accountAuthenticatorResult);
            } else {
                mAccountAuthenticatorResponse.onError(
                    AccountManager.ERROR_CODE_CANCELED,
                    getString(R.string.message_error_operation_canceled));
            }
            mAccountAuthenticatorResponse = null;
        }

        super.finish();
    }

    @CallSuper
    @Override
    public void onCreate(
        @Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAccountAuthenticatorResponse =
            getIntent().getParcelableExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE);

        if (mAccountAuthenticatorResponse != null) {
            mAccountAuthenticatorResponse.onRequestContinued();
        }
    }

    @Nullable
    private AccountAuthenticatorResponse mAccountAuthenticatorResponse;

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PUBLIC)
    @Nullable
    private Bundle mAccountAuthenticatorResult;
}
