package com.vfergus.common.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.val;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.FragmentEvent;
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
public abstract class ExtendedFragment extends Fragment
    implements ViewBinder, ObservableFragment, LifecycleProvider<FragmentEvent> {
    @NonNull
    @Override
    public final Fragment asFragment() {
        return this;
    }

    @Override
    @NonNull
    public final Event<ActivityResultEventArgs> getActivityResultEvent() {
        return mActivityResultEvent;
    }

    @Override
    @NonNull
    public final NoticeEvent getAttachEvent() {
        return mAttachEvent;
    }

    @Override
    @NonNull
    public final Event<BundleEventArgs> getCreateEvent() {
        return mCreateEvent;
    }

    @Override
    @NonNull
    public final Event<BundleEventArgs> getCreateViewEvent() {
        return mCreateViewEvent;
    }

    @Override
    @NonNull
    public final NoticeEvent getDestroyEvent() {
        return mDestroyEvent;
    }

    @Override
    @NonNull
    public final NoticeEvent getDestroyViewEvent() {
        return mDestroyViewEvent;
    }

    @Override
    @NonNull
    public final NoticeEvent getDetachEvent() {
        return mDetachEvent;
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

    @NonNull
    @Override
    public final Event<BundleEventArgs> getViewCreatedEvent() {
        return mViewCreatedEvent;
    }

    @Override
    @NonNull
    public final Event<BundleEventArgs> getViewStateRestoredEvent() {
        return this.mViewStateRestoredEvent;
    }

    @Nullable
    public final AppCompatActivity getSupportActivity() {
        final AppCompatActivity compatActivity;

        final val activity = getActivity();
        if (activity instanceof AppCompatActivity) {
            compatActivity = ((AppCompatActivity) activity);
        } else {
            compatActivity = null;
        }

        return compatActivity;
    }

    @Override
    @NonNull
    @CheckResult
    public final Observable<FragmentEvent> lifecycle() {
        return getLifecycleSubject();
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull final FragmentEvent event) {

        return RxLifecycle.bindUntilEvent(getLifecycleSubject(), event);
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindFragment(getLifecycleSubject());
    }

    @CallSuper
    @Override
    public void bindViews() {
        unbindViews();

        final val view = getView();
        if (view != null) {
            mUnbinder = ButterKnife.bind(this, view);
        }
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

    @CallSuper
    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        val eventArgs = new ActivityResultEventArgs(requestCode, resultCode, data);
        mActivityResultEvent.rise(eventArgs);
    }

    @Override
    public void onRequestPermissionsResult(
        final int requestCode,
        @NonNull final String[] permissions,
        @NonNull final int[] grantResults) {
        //@formatter:off
        super.onRequestPermissionsResult(
            requestCode,permissions,grantResults);
        //@formatter:on

        mPermissionResultEvent.rise(new PermissionResultEventArgs(permissions, grantResults));
    }

    @CallSuper
    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);

        mAttachEvent.rise();

        getLifecycleSubject().onNext(FragmentEvent.ATTACH);
    }

    @CallSuper
    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCreateEvent.rise(new BundleEventArgs(savedInstanceState));

        getLifecycleSubject().onNext(FragmentEvent.CREATE);
    }

    @CallSuper
    @Nullable
    @Override
    public View onCreateView(
        final LayoutInflater inflater,
        @Nullable final ViewGroup container,
        @Nullable final Bundle savedInstanceState) {
        val view = super.onCreateView(inflater, container, savedInstanceState);

        mCreateViewEvent.rise(new BundleEventArgs(savedInstanceState));

        return view;
    }

    @CallSuper
    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewCreatedEvent.rise(new BundleEventArgs(savedInstanceState));

        getLifecycleSubject().onNext(FragmentEvent.CREATE_VIEW);
    }

    @CallSuper
    @Override
    public void onViewStateRestored(@Nullable final Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        mViewStateRestoredEvent.rise(new BundleEventArgs(savedInstanceState));
    }

    @CallSuper
    @Override
    public void onStart() {
        super.onStart();

        mStartEvent.rise();

        getLifecycleSubject().onNext(FragmentEvent.START);
    }

    @Override
    public void onResume() {
        super.onResume();

        mResumeEvent.rise();

        getLifecycleSubject().onNext(FragmentEvent.RESUME);
    }

    @CallSuper
    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);

        mSaveInstanceStateEvent.rise(new BundleEventArgs(outState));
    }

    @CallSuper
    @Override
    public void onPause() {
        super.onPause();

        mPauseEvent.rise();

        getLifecycleSubject().onNext(FragmentEvent.PAUSE);
    }

    @CallSuper
    @Override
    public void onStop() {
        super.onStop();

        mStopEvent.rise();

        getLifecycleSubject().onNext(FragmentEvent.STOP);
    }

    @CallSuper
    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mDestroyViewEvent.rise();

        getLifecycleSubject().onNext(FragmentEvent.DESTROY_VIEW);
    }

    @CallSuper
    @Override
    public void onDestroy() {
        mDestroyEvent.rise();

        getLifecycleSubject().onNext(FragmentEvent.DESTROY);

        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mDetachEvent.rise();

        getLifecycleSubject().onNext(FragmentEvent.DETACH);
    }

    @NonNull
    private final Event<ActivityResultEventArgs> mActivityResultEvent = Events.createEvent();

    @NonNull
    private final NoticeEvent mAttachEvent = Events.createNoticeEvent();

    @NonNull
    private final Event<BundleEventArgs> mCreateEvent = Events.createEvent();

    @NonNull
    private final Event<BundleEventArgs> mCreateViewEvent = Events.createEvent();

    @NonNull
    private final NoticeEvent mDestroyEvent = Events.createNoticeEvent();

    @NonNull
    private final NoticeEvent mDestroyViewEvent = Events.createNoticeEvent();

    @NonNull
    private final NoticeEvent mDetachEvent = Events.createNoticeEvent();

    @Getter(AccessLevel.PRIVATE)
    private final BehaviorSubject<FragmentEvent> mLifecycleSubject = BehaviorSubject.create();

    @NonNull
    private final NoticeEvent mPauseEvent = Events.createNoticeEvent();

    @NonNull
    private final Event<PermissionResultEventArgs> mPermissionResultEvent = Events.createEvent();

    @NonNull
    private final NoticeEvent mResumeEvent = Events.createNoticeEvent();

    @NonNull
    private final Event<BundleEventArgs> mSaveInstanceStateEvent = Events.createEvent();

    @NonNull
    private final NoticeEvent mStartEvent = Events.createNoticeEvent();

    @NonNull
    private final NoticeEvent mStopEvent = Events.createNoticeEvent();

    @NonNull
    private final Event<BundleEventArgs> mViewCreatedEvent = Events.createEvent();

    private final Event<BundleEventArgs> mViewStateRestoredEvent = Events.createEvent();

    @Getter(AccessLevel.PROTECTED)
    @Nullable
    private Unbinder mUnbinder;
}
