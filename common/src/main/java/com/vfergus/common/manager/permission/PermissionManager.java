package com.vfergus.common.manager.permission;

import android.support.annotation.NonNull;

import rx.Observable;

import java.util.List;

public interface PermissionManager {

    @NonNull
    CheckPermissionsResult checkPermissions(@NonNull final List<String> permissions);

    @NonNull
    Observable<RequestPermissionsResult> createPermissionsRequest(
        @NonNull final List<String> permissions);
}
