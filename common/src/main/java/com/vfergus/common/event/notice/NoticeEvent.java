package com.vfergus.common.event.notice;

import android.support.annotation.NonNull;

public interface NoticeEvent {
    void addHandler(@NonNull NoticeEventHandler handler);

    boolean hasHandlers();

    void removeAllHandlers();

    void removeHandler(@NonNull NoticeEventHandler handler);

    void rise();
}
