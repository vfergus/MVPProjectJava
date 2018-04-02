package com.vfergus.facebookclient.api.model;

import lombok.NonNull;

import java.util.List;

public interface DataResponse {

    @NonNull
    List<? extends Post> getData();

    @NonNull
    Paging getPaging();
}
