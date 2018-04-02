package com.vfergus.facebookclient.api.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface Post {
    @NonNull
    From getFrom();

    @NonNull
    String getId();

    @Nullable
    String getMessage();

    @Nullable
    String getPicture();

    @Nullable
    Place getPlace();

    @Nullable
    String getStory();

    @NonNull
    String getUpdatedTime();
}
