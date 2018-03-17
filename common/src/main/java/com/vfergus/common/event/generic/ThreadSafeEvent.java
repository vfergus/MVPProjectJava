package com.vfergus.common.event.generic;

import android.support.annotation.NonNull;

import lombok.val;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

public final class ThreadSafeEvent<TEventArgs> implements Event<TEventArgs> {
    @Override
    public final void addHandler(@NonNull final EventHandler<TEventArgs> handler) {
        this.handlers.add(handler);
    }

    @Override
    public final void removeHandler(@NonNull final EventHandler<TEventArgs> handler) {
        this.handlers.remove(handler);
    }

    @Override
    public final boolean hasHandlers() {
        return !this.handlers.isEmpty();
    }

    @Override
    public final void removeAllHandlers() {
        this.handlers.clear();
    }

    @Override
    public final void rise(@NonNull final TEventArgs eventArgs) {
        for (final val handler : this.handlers) {
            handler.onEvent(eventArgs);
        }
    }

    @NonNull
    private final Collection<EventHandler<TEventArgs>> handlers = new CopyOnWriteArrayList<>();
}
