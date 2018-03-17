package com.vfergus.common.view.fragment;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.vfergus.common.event.eventArgs.ActivityResultEventArgs;
import com.vfergus.common.event.eventArgs.BundleEventArgs;
import com.vfergus.common.event.eventArgs.PermissionResultEventArgs;
import com.vfergus.common.event.generic.Event;
import com.vfergus.common.event.notice.NoticeEvent;

public interface ObservableFragment {
    @NonNull
    Fragment asFragment();

    @NonNull
    Event<ActivityResultEventArgs> getActivityResultEvent();

    @NonNull
    NoticeEvent getAttachEvent();

    @NonNull
    Event<BundleEventArgs> getCreateEvent();

    @NonNull
    Event<BundleEventArgs> getCreateViewEvent();

    @NonNull
    NoticeEvent getDestroyEvent();

    @NonNull
    NoticeEvent getDestroyViewEvent();

    @NonNull
    NoticeEvent getDetachEvent();

    @NonNull
    NoticeEvent getPauseEvent();

    @NonNull
    Event<PermissionResultEventArgs> getPermissionResultEvent();

    @NonNull
    NoticeEvent getResumeEvent();

    @NonNull
    Event<BundleEventArgs> getSaveInstanceStateEvent();

    @NonNull
    NoticeEvent getStartEvent();

    @NonNull
    NoticeEvent getStopEvent();

    @NonNull
    Event<BundleEventArgs> getViewCreatedEvent();

    @NonNull
    Event<BundleEventArgs> getViewStateRestoredEvent();
}
