package com.vfergus.facebookclient.api.model.entity;

import android.support.annotation.Nullable;

import lombok.Getter;
import lombok.Setter;

import com.google.gson.annotations.SerializedName;
import com.vfergus.facebookclient.api.model.Location;

public class LocationEntity implements Location {

    @SerializedName("city")
    @Getter(onMethod = @__(@Override))
    @Setter
    @Nullable
    private String city;

    @SerializedName("country")
    @Getter(onMethod = @__(@Override))
    @Setter
    @Nullable
    private String country;

    @SerializedName("latitude")
    @Getter(onMethod = @__(@Override))
    @Setter
    private double latitude;

    @SerializedName("longitude")
    @Getter(onMethod = @__(@Override))
    @Setter
    private double longitude;
}
