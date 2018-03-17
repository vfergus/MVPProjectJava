package com.vfergus.common.event.eventArgs;

import android.content.Intent;
import android.support.annotation.Nullable;

import lombok.Getter;

public class ActivityResultEventArgs {
    public ActivityResultEventArgs(
        final int requestCode, final int resultCode, @Nullable final Intent data) {
        this.data = data;
        this.requestCode = requestCode;
        this.resultCode = resultCode;
    }

    @Getter
    @Nullable
    private final Intent data;

    @Getter
    private final int requestCode;

    @Getter
    private final int resultCode;
}
