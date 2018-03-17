package com.vfergus.twitterclient.core.manager.rx;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import rx.Observable;
import rx.Scheduler;

public interface RxManager {
    @CheckResult
    @NonNull
    <T> Observable<T> autoManage(@NonNull Observable<T> observable);

    @NonNull
    Scheduler getComputationScheduler();

    @NonNull
    Scheduler getIOScheduler();

    @NonNull
    Scheduler getUIScheduler();
}
