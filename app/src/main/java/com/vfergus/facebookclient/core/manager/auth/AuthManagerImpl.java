package com.vfergus.facebookclient.core.manager.auth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountsException;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import lombok.val;

import com.vfergus.common.utils.tuple.Tuple2;
import com.vfergus.facebookclient.core.manager.account.AccountConstants;
import com.vfergus.facebookclient.core.manager.account.AppAccount;
import com.vfergus.facebookclient.core.manager.facebookAuth.FacebookAuthManager;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class AuthManagerImpl implements AuthManager {

    public AuthManagerImpl(
        @NonNull final FacebookAuthManager facebookAuthManager,
        @NonNull final AccountManager accountManager) {
        mFacebookAuthManager = facebookAuthManager;
        mAccountManager = accountManager;
    }

    @NonNull
    @Override
    public Observable<AuthResult> loginWithFacebook() {
        return mFacebookAuthManager
            .getAccessToken()
            .flatMap(new Function<Tuple2<String, String>, Observable<AppAccount>>() {
                @Override
                public Observable<AppAccount> apply(final Tuple2<String, String> tuple2)
                    throws Exception {
                    final String userName = tuple2.get1();
                    final String token = tuple2.get2();
                    if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(token)) {
                        return addAccount(userName, token);
                    } else {
                        return Observable.error(new AccountsException("Account is not added"));
                    }
                }
            })
            .map(new Function<AppAccount, AuthResult>() {
                @Override
                public AuthResult apply(final AppAccount appAccount)
                    throws Exception {
                    return new AuthResult(AccountConstants.ACCOUNT_TYPE, appAccount.getUserName());
                }
            });
    }

    @NonNull
    private final AccountManager mAccountManager;

    @NonNull
    private final FacebookAuthManager mFacebookAuthManager;

    @NonNull
    private Observable<AppAccount> addAccount(
        @NonNull final String userName, @NonNull final String token) {
        val account = new Account(userName, AccountConstants.ACCOUNT_TYPE);
        final boolean added = mAccountManager.addAccountExplicitly(account, null, null);
        if (added) {
            mAccountManager.setAuthToken(account, AccountConstants.TOKEN_TYPE_ACCESS_TOKEN, token);
            return Observable.just(new AppAccount(account.name));
        } else {
            return Observable.error(new AccountsException("Account is not added"));
        }
    }
}
