package com.vfergus.facebookclient.api.model.entity;

import android.support.annotation.NonNull;

import lombok.Getter;
import lombok.Setter;

import com.google.gson.annotations.SerializedName;
import com.vfergus.facebookclient.api.model.From;

public class FromEntity implements From {
    @SerializedName("id")
    @Getter(onMethod = @__(@Override))
    @Setter
    private long id;

    @SerializedName("name")
    @Getter(onMethod = @__(@Override))
    @Setter
    @NonNull
    private String name;
}
