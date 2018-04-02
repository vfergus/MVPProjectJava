package com.vfergus.facebookclient.api.model;

import lombok.NonNull;

public interface Place {
    @NonNull
    String getId();

    @NonNull
    Location getLocation();

    @NonNull
    String getName();
}
