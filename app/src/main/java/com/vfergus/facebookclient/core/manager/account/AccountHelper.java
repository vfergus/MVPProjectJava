package com.vfergus.facebookclient.core.manager.account;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public interface AccountHelper {
    @NonNull
    <T> Observable<T> withToken(@NonNull Function<String, Observable<T>> method);
}
