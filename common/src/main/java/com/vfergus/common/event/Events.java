package com.vfergus.common.event;

import android.support.annotation.NonNull;

import com.vfergus.common.event.generic.Event;
import com.vfergus.common.event.generic.ThreadSafeEvent;
import com.vfergus.common.event.notice.NoticeEvent;
import com.vfergus.common.event.notice.ThreadSafeNoticeEvent;

public final class Events {
    @NonNull
    public static NoticeEvent createNoticeEvent() {
        return new ThreadSafeNoticeEvent();
    }

    @NonNull
    public static <TEventArgs> Event<TEventArgs> createEvent() {
        return new ThreadSafeEvent<>();
    }

    @NonNull
    public static NoticeEvent createThreadSafeNoticeEvent() {
        return new ThreadSafeNoticeEvent();
    }

    @NonNull
    public static <TEventArgs> Event<TEventArgs> createThreadSafeEvent() {
        return new ThreadSafeEvent<>();
    }

    private Events() {

    }
}
