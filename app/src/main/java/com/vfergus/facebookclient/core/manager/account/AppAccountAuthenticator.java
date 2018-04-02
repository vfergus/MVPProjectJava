package com.vfergus.facebookclient.core.manager.account;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.os.Bundle;

import lombok.NonNull;
import lombok.experimental.Accessors;
import lombok.experimental.var;
import lombok.val;

import com.vfergus.facebookclient.view.activity.LoginActivity;

@Accessors(prefix = "m")
public class AppAccountAuthenticator extends AbstractAccountAuthenticator {

    public AppAccountAuthenticator(@NonNull final Context context) {
        super(context);

        mContext = context;
    }

    @Override
    public Bundle editProperties(
        final AccountAuthenticatorResponse response, final String accountType) {
        return null;
    }

    @Override
    public Bundle addAccount(
        final AccountAuthenticatorResponse response,
        final String accountType,
        final String authTokenType,
        final String[] requiredFeatures,
        final Bundle options)
        throws NetworkErrorException {
        val result = new Bundle();

        val intent = LoginActivity.getIntent(mContext, accountType);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
        result.putParcelable(AccountManager.KEY_INTENT, intent);

        return result;
    }

    @Override
    public Bundle confirmCredentials(
        final AccountAuthenticatorResponse response, final Account account, final Bundle options)
        throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle getAuthToken(
        final AccountAuthenticatorResponse response,
        final Account account,
        final String authTokenType,
        final Bundle options)
        throws NetworkErrorException {

        val accountManager = AccountManager.get(mContext);

        var authToken = accountManager.peekAuthToken(account, authTokenType);

        final Bundle result = new Bundle(3);
        result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
        result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
        result.putString(AccountManager.KEY_AUTHTOKEN, authToken);

        return result;
    }

    @Override
    public String getAuthTokenLabel(final String authTokenType) {
        return null;
    }

    @Override
    public Bundle updateCredentials(
        final AccountAuthenticatorResponse response,
        final Account account,
        final String authTokenType,
        final Bundle options)
        throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle hasFeatures(
        final AccountAuthenticatorResponse response, final Account account, final String[] features)
        throws NetworkErrorException {
        return null;
    }

    @NonNull
    private final Context mContext;
}
