package com.vfergus.twitterclient.api;

import rx.Observable;

import javax.annotation.Nonnull;

import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface TwitterApi {

    @GET(TwitterConstants.STATUS + "/" + TwitterConstants.USER_TIMELINE)
    @Nonnull
    Observable<ResponseBody> getUserTimeline();
}
