package com.vfergus.common.event.notice;

import android.support.annotation.NonNull;

import lombok.val;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

public final class ThreadSafeNoticeEvent implements NoticeEvent {
    @Override
    public final void addHandler(@NonNull final NoticeEventHandler handler) {
        this.handlers.add(handler);
    }

    @Override
    public final void removeHandler(@NonNull final NoticeEventHandler handler) {
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
    public final void rise() {
        for (final val handler : this.handlers) {
            handler.onEvent();
        }
    }

    @NonNull
    private final Collection<NoticeEventHandler> handlers = new CopyOnWriteArrayList<>();
}
