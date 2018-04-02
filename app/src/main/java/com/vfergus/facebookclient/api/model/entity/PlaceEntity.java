package com.vfergus.facebookclient.api.model.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import com.google.gson.annotations.SerializedName;
import com.vfergus.facebookclient.api.model.Place;

public class PlaceEntity implements Place {
    @SerializedName("id")
    @Getter(onMethod = @__(@Override))
    @Setter
    @NonNull
    private String id;

    @SerializedName("location")
    @Getter(onMethod = @__(@Override))
    @Setter
    @NonNull
    private LocationEntity location;

    @SerializedName("name")
    @Getter(onMethod = @__(@Override))
    @Setter
    @NonNull
    private String name;
}
