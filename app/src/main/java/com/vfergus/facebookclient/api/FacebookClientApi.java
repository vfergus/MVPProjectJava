package com.vfergus.facebookclient.api;

import android.support.annotation.NonNull;

import com.vfergus.facebookclient.api.model.entity.DataResponseEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FacebookClientApi {

    @GET(FacebookClientConstants.USER_POSTS)
    @NonNull
    Observable<DataResponseEntity> getUserPosts(@Query("access_token") String token);
}
