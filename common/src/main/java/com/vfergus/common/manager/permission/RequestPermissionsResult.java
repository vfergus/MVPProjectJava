package com.vfergus.common.manager.permission;

import android.support.annotation.NonNull;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(prefix = "m")
public class RequestPermissionsResult {

    public RequestPermissionsResult(
        @NonNull final List<String> deniedPermissions,
        @NonNull final List<String> grantedPermissions) {
        mDeniedPermissions = deniedPermissions;
        mGrantedPermissions = grantedPermissions;
    }

    public boolean isAllGranted() {
        return !getGrantedPermissions().isEmpty() && getDeniedPermissions().isEmpty();
    }

    @Getter
    @NonNull
    private final List<String> mDeniedPermissions;

    @Getter
    @NonNull
    private final List<String> mGrantedPermissions;
}
