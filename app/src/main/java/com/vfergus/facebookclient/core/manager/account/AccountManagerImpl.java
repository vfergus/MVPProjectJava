package com.vfergus.facebookclient.core.manager.account;

import android.accounts.Account;
import android.accounts.AccountsException;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import lombok.experimental.Accessors;
import lombok.val;

import com.vfergus.common.view.activity.ObservableActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;

@Accessors(prefix = "m")
public class AccountManagerImpl implements AccountManager {
    public AccountManagerImpl(
        @NonNull final String accountType,
        @NonNull final android.accounts.AccountManager nativeAccountManager,
        @NonNull final ObservableActivity activity) {

        mAccountType = accountType;
        mNativeAccountManager = nativeAccountManager;
        mActivity = activity;
    }

    @NonNull
    @Override
    public Observable<AppAccount> addAccount(
        @Nullable final String name) {
        return Observable.create(new ObservableOnSubscribe<AppAccount>() {
            @Override
            public void subscribe(final ObservableEmitter<AppAccount> emitter)
                throws Exception {
                try {
                    Bundle bundle = null;
                    if (!TextUtils.isEmpty(name)) {
                        bundle = new Bundle(1);
                        bundle.putString(android.accounts.AccountManager.KEY_ACCOUNT_NAME, name);
                    }
                    val accountManagerFuture = mNativeAccountManager.addAccount(getAccountType(),
                                                                                AccountConstants
                                                                                    .TOKEN_TYPE_ACCESS_TOKEN,
                                                                                null,
                                                                                bundle,
                                                                                (Activity)
                                                                                    mActivity,
                                                                                null,
                                                                                null);

                    if (accountManagerFuture.isDone()) {
                        val result = accountManagerFuture.getResult();
                        if (result != null) {
                            val name =
                                result.getString(android.accounts.AccountManager.KEY_ACCOUNT_NAME);
                            if (!TextUtils.isEmpty(name)) {
                                emitter.onNext(new AppAccount(name));
                            } else {
                                emitter.onNext(null);
                            }
                        }
                        emitter.onNext(null);
                        emitter.onComplete();
                    }
                } catch (AuthenticatorException | IOException | OperationCanceledException error) {
                    emitter.onError(error);
                }
            }
        });
    }

    @Nullable
    @Override
    public AppAccount getAccountByName(@NonNull final String name) {
        val nativeAccount = getNativeAccountByName(name);
        if (nativeAccount != null) {
            return new AppAccount(nativeAccount.name);
        } else {
            return null;
        }
    }

    @NonNull
    @Override
    public String getAccountType() {
        return mAccountType;
    }

    @Nullable
    @Override
    public List<AppAccount> getAccounts() {
        val accounts = mNativeAccountManager.getAccountsByType(getAccountType());
        val length = accounts.length;
        if (length > 0) {
            val accountsList = new ArrayList<AppAccount>(length);
            for (val account : accounts) {
                accountsList.add(new AppAccount(account.name));
            }
            return accountsList;
        } else {
            return null;
        }
    }

    @NonNull
    @Override
    public Observable<String> getToken(
        @NonNull final AppAccount appAccount) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter)
                throws Exception {
                val account = getNativeAccountByName(appAccount.getUserName());
                if (account != null) {
                    try {
                        val authToken = mNativeAccountManager.blockingGetAuthToken(account,
                                                                                   AccountConstants.TOKEN_TYPE_ACCESS_TOKEN,
                                                                                   false);
                        emitter.onNext(authToken);
                        emitter.onComplete();
                    } catch (final Exception error) {
                        emitter.onError(error);
                    }
                } else {
                    emitter.onError(new AccountsException("Account not exists"));
                }
            }
        });
    }

    @Override
    public void invalidateToken(@NonNull final String authToken) {
        mNativeAccountManager.invalidateAuthToken(getAccountType(), authToken);
    }

    @NonNull
    @Override
    public Observable<Boolean> removeAccount(
        @NonNull final AppAccount account) {
        return Observable.unsafeCreate(new ObservableSource<Boolean>() {
            @Override
            public void subscribe(final Observer<? super Boolean> observer) {
                try {
                    val nativeAccountByName = getNativeAccountByName(account.getUserName());
                    final boolean removed;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                        removed = mNativeAccountManager
                            .removeAccount(nativeAccountByName, (Activity) mActivity, null, null)
                            .getResult()
                            .getBoolean(android.accounts.AccountManager.KEY_BOOLEAN_RESULT);
                    } else {
                        removed = mNativeAccountManager
                            .removeAccount(nativeAccountByName, null, null)
                            .getResult();
                    }
                    observer.onNext(removed);
                    observer.onComplete();
                } catch (final Exception error) {
                    observer.onError(error);
                }
            }
        });
    }

    @CallSuper
    @Nullable
    protected Account getNativeAccountByName(@NonNull final String name) {
        Account account = null;
        val accountsByType = mNativeAccountManager.getAccountsByType(getAccountType());
        for (final val accountByName : accountsByType) {
            if (accountByName.name.equals(name)) {
                account = accountByName;
                break;
            }
        }

        return account;
    }

    @NonNull
    private final String mAccountType;

    @NonNull
    private final ObservableActivity mActivity;

    @NonNull
    private final android.accounts.AccountManager mNativeAccountManager;
}
