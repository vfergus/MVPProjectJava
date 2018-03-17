package com.vfergus.twitterclient.core.manager.api;

import com.vfergus.twitterclient.api.TwitterApi;

import javax.annotation.Nonnull;

public interface ApiManager {

    @Nonnull
    TwitterApi getApi();
}
