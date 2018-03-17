package com.vfergus.common.event.generic;

import android.support.annotation.NonNull;

public interface EventHandler<TEventArgs> {
    void onEvent(@NonNull TEventArgs eventArgs);
}
