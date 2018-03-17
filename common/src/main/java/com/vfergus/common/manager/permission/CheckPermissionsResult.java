package com.vfergus.common.manager.permission;

import android.support.annotation.NonNull;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(prefix = "m")
public class CheckPermissionsResult {

    public boolean isAllGranted() {
        return !getGrantedPermissions().isEmpty() && getDeniedPermissions().isEmpty();
    }

    public CheckPermissionsResult(
        @NonNull final List<String> deniedPermissions,
        @NonNull final List<String> grantedPermissions,
        @NonNull final List<String> neverAskAgainPermissions) {

        mDeniedPermissions = deniedPermissions;
        mGrantedPermissions = grantedPermissions;
        mNeverAskAgainPermissions = neverAskAgainPermissions;
    }

    @Getter
    @NonNull
    private final List<String> mDeniedPermissions;

    @Getter
    @NonNull
    private final List<String> mGrantedPermissions;

    @Getter
    @NonNull
    private final List<String> mNeverAskAgainPermissions;
}
