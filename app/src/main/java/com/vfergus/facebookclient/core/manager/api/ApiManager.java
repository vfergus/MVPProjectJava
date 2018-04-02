package com.vfergus.facebookclient.core.manager.api;

import android.support.annotation.NonNull;

import com.vfergus.facebookclient.api.FacebookClientApi;

public interface ApiManager {

    @NonNull
    FacebookClientApi getApi();
}
