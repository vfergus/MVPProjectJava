package com.vfergus.facebookclient.core.manager.message;

import android.support.annotation.NonNull;

import com.vfergus.common.view.activity.ObservableActivity;

public class MessageManagerImpl implements MessageManager {
    public MessageManagerImpl(@NonNull final ObservableActivity observableActivity) {
        mObservableActivity = observableActivity;
    }

    @Override
    public void displayMessage(final int resMessage) {

    }

    @Override
    public void displayMessage(@NonNull final String message) {

    }

    @Override
    public void showErrorMessage(final int resMessage) {

    }

    @Override
    public void showErrorMessage(@NonNull final String message) {

    }

    @NonNull
    private final ObservableActivity mObservableActivity;
}
