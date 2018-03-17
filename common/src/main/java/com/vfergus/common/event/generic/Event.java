package com.vfergus.common.event.generic;

import android.support.annotation.NonNull;

public interface Event<TEventArgs> {
    void addHandler(@NonNull EventHandler<TEventArgs> handler);

    void removeHandler(@NonNull EventHandler<TEventArgs> handler);

    boolean hasHandlers();

    void removeAllHandlers();

    void rise(@NonNull TEventArgs eventArgs);
}
