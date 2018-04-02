package com.vfergus.facebookclient.api.model.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import com.google.gson.annotations.SerializedName;
import com.vfergus.facebookclient.api.model.DataResponse;
import com.vfergus.facebookclient.api.model.Paging;

import java.util.List;

public class DataResponseEntity implements DataResponse {

    @SerializedName("data")
    @Getter(onMethod = @__(@Override))
    @Setter
    @NonNull
    private List<PostEntity> data;

    @SerializedName("paging")
    @Getter(onMethod = @__(@Override))
    @Setter
    @NonNull
    private Paging paging;
}
