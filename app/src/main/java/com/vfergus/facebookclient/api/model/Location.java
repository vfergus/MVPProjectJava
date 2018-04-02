package com.vfergus.facebookclient.api.model;

import android.support.annotation.Nullable;

public interface Location {
    @Nullable
    String getCity();

    @Nullable
    String getCountry();

    double getLatitude();

    double getLongitude();
}
