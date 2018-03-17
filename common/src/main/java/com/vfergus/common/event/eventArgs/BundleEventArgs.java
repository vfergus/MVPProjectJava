package com.vfergus.common.event.eventArgs;

import android.os.Bundle;
import android.support.annotation.Nullable;

import lombok.Getter;

public class BundleEventArgs {
    public BundleEventArgs(@Nullable final Bundle data) {
        this.data = data;
    }

    @Getter
    @Nullable
    private final Bundle data;
}
