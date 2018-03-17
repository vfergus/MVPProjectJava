package com.vfergus.common.view.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.vfergus.common.event.eventArgs.ActivityResultEventArgs;
import com.vfergus.common.event.eventArgs.BundleEventArgs;
import com.vfergus.common.event.eventArgs.PermissionResultEventArgs;
import com.vfergus.common.event.generic.Event;
import com.vfergus.common.event.notice.NoticeEvent;

public interface ObservableActivity {
    @NonNull
    AppCompatActivity asActivity();

    @NonNull
    Event<ActivityResultEventArgs> getActivityResultEvent();

    @NonNull
    Event<BundleEventArgs> getCreateEvent();

    @NonNull
    NoticeEvent getDestroyEvent();

    @NonNull
    NoticeEvent getPauseEvent();

    @NonNull
    Event<PermissionResultEventArgs> getPermissionResultEvent();

    @NonNull
    Event<BundleEventArgs> getRestoreInstanceStateEvent();

    @NonNull
    NoticeEvent getResumeEvent();

    @NonNull
    Event<BundleEventArgs> getSaveInstanceStateEvent();

    @NonNull
    NoticeEvent getStartEvent();

    @NonNull
    NoticeEvent getStopEvent();
}
