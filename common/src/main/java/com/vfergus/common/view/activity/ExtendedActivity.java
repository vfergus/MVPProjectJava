package com.vfergus.common.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.val;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import com.vfergus.common.event.Events;
import com.vfergus.common.event.eventArgs.ActivityResultEventArgs;
import com.vfergus.common.event.eventArgs.BundleEventArgs;
import com.vfergus.common.event.eventArgs.PermissionResultEventArgs;
import com.vfergus.common.event.generic.Event;
import com.vfergus.common.event.notice.NoticeEvent;
import com.vfergus.common.view.ViewBinder;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

@Accessors(prefix = "m")
public abstract class ExtendedActivity extends AppCompatActivity
    implements ViewBinder, ObservableActivity, LifecycleProvider<ActivityEvent> {

    @NonNull
    @Override
    public final AppCompatActivity asActivity() {
        return this;
    }

    @Override
    @NonNull
    public final Event<ActivityResultEventArgs> getActivityResultEvent() {
        return mActivityResultEvent;
    }

    @Override
    @NonNull
    public final Event<BundleEventArgs> getCreateEvent() {
        return mCreateEvent;
    }

    @Override
    @NonNull
    public final NoticeEvent getDestroyEvent() {
        return mDestroyEvent;
    }

    @Override
    @NonNull
    public final NoticeEvent getPauseEvent() {
        return mPauseEvent;
    }

    @NonNull
    @Override
    public final Event<PermissionResultEventArgs> getPermissionResultEvent() {
        return mPermissionResultEvent;
    }

    @Override
    @NonNull
    public final Event<BundleEventArgs> getRestoreInstanceStateEvent() {
        return mRestoreInstanceStateEvent;
    }

    @Override
    @NonNull
    public final NoticeEvent getResumeEvent() {
        return mResumeEvent;
    }

    @Override
    @NonNull
    public final Event<BundleEventArgs> getSaveInstanceStateEvent() {
        return mSaveInstanceStateEvent;
    }

    @Override
    @NonNull
    public final NoticeEvent getStartEvent() {
        return mStartEvent;
    }

    @Override
    @NonNull
    public final NoticeEvent getStopEvent() {
        return mStopEvent;
    }

    @Override
    @NonNull
    @CheckResult
    public final Observable<ActivityEvent> lifecycle() {
        return getLifecycleSubject();
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull final ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(getLifecycleSubject(), event);
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindActivity(getLifecycleSubject());
    }

    @CallSuper
    @Override
    public void bindViews() {
        unbindViews();

        mUnbinder = ButterKnife.bind(this);
    }

    @CallSuper
    @Override
    public void bindViews(@NonNull final View source) {
        unbindViews();

        mUnbinder = ButterKnife.bind(this, source);
    }

    @CallSuper
    @Override
    public void unbindViews() {
        final val unbinder = getUnbinder();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    public void setSubtitle(@StringRes final int subtitleId) {
        final val actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setSubtitle(subtitleId);
        }

        final val supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setSubtitle(subtitleId);
        }
    }

    public void setSubtitle(@Nullable final String subtitle) {
        final val actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setSubtitle(subtitle);
        }

        final val supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setSubtitle(subtitle);
        }
    }

    @CallSuper
    @Override
    protected void onActivityResult(
        final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        val eventArgs = new ActivityResultEventArgs(requestCode, resultCode, data);
        mActivityResultEvent.rise(eventArgs);
    }

    @CallSuper
    @Override
    protected void onPause() {
        super.onPause();

        mPauseEvent.rise();

        getLifecycleSubject().onNext(ActivityEvent.PAUSE);
    }

    @CallSuper
    @Override
    protected void onResume() {
        super.onResume();

        mResumeEvent.rise();

        getLifecycleSubject().onNext(ActivityEvent.RESUME);
    }

    @Override
    public void onRequestPermissionsResult(
        final int requestCode,
        @NonNull final String[] permissions,
        @NonNull final int[] grantResults) {
        //@formatter:off
        super.onRequestPermissionsResult(
            requestCode,
           permissions, grantResults);
        //@formatter:on

        mPermissionResultEvent.rise(new PermissionResultEventArgs(permissions, grantResults));
    }

    @CallSuper
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCreateEvent.rise(new BundleEventArgs(savedInstanceState));

        getLifecycleSubject().onNext(ActivityEvent.CREATE);
    }

    @CallSuper
    @Override
    protected void onStart() {
        super.onStart();

        mStartEvent.rise();

        getLifecycleSubject().onNext(ActivityEvent.START);
    }

    @CallSuper
    @Override
    protected void onStop() {
        super.onStop();

        getLifecycleSubject().onNext(ActivityEvent.STOP);

        mStopEvent.rise();
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        mDestroyEvent.rise();

        getLifecycleSubject().onNext(ActivityEvent.DESTROY);

        super.onDestroy();
    }

    @CallSuper
    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);

        mSaveInstanceStateEvent.rise(new BundleEventArgs(outState));
    }

    @CallSuper
    @Override
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mRestoreInstanceStateEvent.rise(new BundleEventArgs(savedInstanceState));
    }

    @NonNull
    private final Event<ActivityResultEventArgs> mActivityResultEvent = Events.createEvent();

    @NonNull
    private final Event<BundleEventArgs> mCreateEvent = Events.createEvent();

    @NonNull
    private final NoticeEvent mDestroyEvent = Events.createNoticeEvent();

    @Getter(AccessLevel.PRIVATE)
    private final BehaviorSubject<ActivityEvent> mLifecycleSubject = BehaviorSubject.create();

    @NonNull
    private final NoticeEvent mPauseEvent = Events.createNoticeEvent();

    @NonNull
    private final Event<PermissionResultEventArgs> mPermissionResultEvent = Events.createEvent();

    @NonNull
    private final Event<BundleEventArgs> mRestoreInstanceStateEvent = Events.createEvent();

    @NonNull
    private final NoticeEvent mResumeEvent = Events.createNoticeEvent();

    @NonNull
    private final Event<BundleEventArgs> mSaveInstanceStateEvent = Events.createEvent();

    @NonNull
    private final NoticeEvent mStartEvent = Events.createNoticeEvent();

    @NonNull
    private final NoticeEvent mStopEvent = Events.createNoticeEvent();

    @Getter(AccessLevel.PROTECTED)
    @Nullable
    private Unbinder mUnbinder;
}
