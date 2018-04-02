package com.vfergus.facebookclient.core.manager.rx;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

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
