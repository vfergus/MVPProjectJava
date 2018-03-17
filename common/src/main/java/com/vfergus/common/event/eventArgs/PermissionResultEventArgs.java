package com.vfergus.common.event.eventArgs;

import android.support.annotation.NonNull;

import lombok.Getter;

public class PermissionResultEventArgs extends EventArgs {
    public PermissionResultEventArgs(
        @NonNull final String[] permissions, @NonNull final int[] grantResults) {

        this.permissions = permissions;
        this.grantResults = grantResults;
    }

    @Getter
    @NonNull
    private final int[] grantResults;

    @Getter
    @NonNull
    private final String[] permissions;
}
