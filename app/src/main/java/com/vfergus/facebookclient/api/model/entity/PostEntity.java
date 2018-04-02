package com.vfergus.facebookclient.api.model.entity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import lombok.Getter;
import lombok.Setter;

import com.google.gson.annotations.SerializedName;
import com.vfergus.facebookclient.api.model.Post;

public class PostEntity implements Post {
    @SerializedName("from")
    @Getter(onMethod = @__(@Override))
    @Setter
    @NonNull
    private FromEntity from;

    @SerializedName("id")
    @Getter(onMethod = @__(@Override))
    @Setter
    @NonNull
    private String id;

    @SerializedName("message")
    @Getter(onMethod = @__(@Override))
    @Setter
    @Nullable
    private String message;

    @SerializedName("full_picture")
    @Getter(onMethod = @__(@Override))
    @Setter
    @Nullable
    private String picture;

    @SerializedName("place")
    @Getter(onMethod = @__(@Override))
    @Setter
    @Nullable
    private PlaceEntity place;

    @SerializedName("story")
    @Getter(onMethod = @__(@Override))
    @Setter
    @Nullable
    private String story;

    @SerializedName("updated_time")
    @Getter(onMethod = @__(@Override))
    @Setter
    @NonNull
    private String updatedTime;

}
