package com.vfergus.twitterclient.core.manager.api;

import android.content.Context;
import android.support.annotation.NonNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

import com.vfergus.twitterclient.api.TwitterApi;

@Accessors(prefix = "m")
public class ApiManagerImpl implements ApiManager {

    public ApiManagerImpl(
        @NonNull final Context context, @NonNull final TwitterApi api) {
        mContext = context;
        mApi = api;
    }

    @Getter(onMethod = @__(@Override))
    @NonNull
    private final TwitterApi mApi;

    @Getter(AccessLevel.PROTECTED)
    @NonNull
    private final Context mContext;
}
