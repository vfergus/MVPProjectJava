package com.vfergus.facebookclient.core.manager.account;

import android.accounts.AccountsException;
import android.support.annotation.NonNull;

import lombok.val;

import java.net.HttpURLConnection;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import retrofit2.HttpException;

public class AccountHelperImpl implements AccountHelper {

    public AccountHelperImpl(@NonNull final AccountManager accountManager) {
        mAccountManager = accountManager;
    }

    @Override
    @NonNull
    public <T> Observable<T> withToken(@NonNull final Function<String, Observable<T>> method) {
        val accounts = mAccountManager.getAccounts();
        if (accounts != null && !accounts.isEmpty()) {
            val account = accounts.get(0);
            return mAccountManager.getToken(account).flatMap(new Function<String, Observable<T>>() {
                @Override
                public Observable<T> apply(final String token)
                    throws Exception {
                    return method.apply(token).doOnError(new Consumer<Throwable>() {
                        @Override
                        public void accept(final Throwable throwable)
                            throws Exception {
                            if (throwable instanceof HttpException) {
                                final val httpException = (HttpException) throwable;
                                if (httpException.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                                    mAccountManager.invalidateToken(token);
                                }
                            }
                        }
                    });
                }
            });
        } else {
            return Observable.error(new AccountsException("Account not exist."));
        }
    }

    @NonNull
    private final AccountManager mAccountManager;
}
