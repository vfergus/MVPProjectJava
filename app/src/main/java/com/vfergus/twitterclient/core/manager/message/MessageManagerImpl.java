package com.vfergus.twitterclient.core.manager.message;

import com.vfergus.common.view.activity.ObservableActivity;

import javax.annotation.Nonnull;

public class MessageManagerImpl implements MessageManager {
    public MessageManagerImpl(@Nonnull final ObservableActivity observableActivity) {
        mObservableActivity = observableActivity;
    }

    @Nonnull
    private final ObservableActivity mObservableActivity;
}
