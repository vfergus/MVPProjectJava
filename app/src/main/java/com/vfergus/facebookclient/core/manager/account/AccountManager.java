package com.vfergus.facebookclient.core.manager.account;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import io.reactivex.Observable;

public interface AccountManager {
    @NonNull
    Observable<AppAccount> addAccount(
        @Nullable String userName);

    @Nullable
    AppAccount getAccountByName(@NonNull String name);

    @NonNull
    String getAccountType();

    @Nullable
    List<AppAccount> getAccounts();

    @NonNull
    Observable<String> getToken(@NonNull AppAccount account);

    void invalidateToken(@NonNull String authToken);

    @NonNull
    Observable<Boolean> removeAccount(@NonNull AppAccount account);
}
