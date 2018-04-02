package com.vfergus.facebookclient.core.manager.auth;

import android.support.annotation.NonNull;

import io.reactivex.Observable;

public interface AuthManager {
    @NonNull
    Observable<AuthResult> loginWithFacebook();
}