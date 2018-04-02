package com.vfergus.facebookclient.core.manager.facebookAuth;

import android.support.annotation.NonNull;

import com.vfergus.common.utils.tuple.Tuple2;

import io.reactivex.Observable;

public interface FacebookAuthManager {

    @NonNull
    Observable<Tuple2<String, String>> getAccessToken();
}
