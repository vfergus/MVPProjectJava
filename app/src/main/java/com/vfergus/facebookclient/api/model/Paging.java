package com.vfergus.facebookclient.api.model;

import android.support.annotation.Nullable;

import lombok.Getter;
import lombok.Setter;

import com.google.gson.annotations.SerializedName;

public class Paging {

    @SerializedName("next")
    @Getter
    @Setter
    @Nullable
    private String next;

    @SerializedName("previous")
    @Getter
    @Setter
    @Nullable
    private String previous;
}
