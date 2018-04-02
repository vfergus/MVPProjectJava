package com.vfergus.facebookclient.core.manager.rx;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

import com.trello.rxlifecycle2.LifecycleProvider;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Accessors(prefix = "m")
public class RxManagerImpl<TLifecycleEvent> implements RxManager {
    public RxManagerImpl(
        @NonNull final LifecycleProvider<TLifecycleEvent> lifecycleProvider) {

        mLifecycleProvider = lifecycleProvider;
    }

    @CheckResult
    @NonNull
    @Override
    public <T> Observable<T> autoManage(@NonNull final Observable<T> observable) {

        return observable.compose(getLifecycleProvider().<T>bindToLifecycle());
    }

    @NonNull
    @Override
    public Scheduler getComputationScheduler() {
        return Schedulers.computation();
    }

    @NonNull
    @Override
    public Scheduler getIOScheduler() {
        return Schedulers.io();
    }

    @NonNull
    @Override
    public Scheduler getUIScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Getter(AccessLevel.PROTECTED)
    @NonNull
    private final LifecycleProvider<TLifecycleEvent> mLifecycleProvider;
}
