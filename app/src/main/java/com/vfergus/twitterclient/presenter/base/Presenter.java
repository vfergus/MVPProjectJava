package com.vfergus.twitterclient.presenter.base;

import android.support.annotation.NonNull;

import com.vfergus.twitterclient.view.Screen;

public interface Presenter<TScreen extends Screen> {
    void attachScreen(@NonNull TScreen screen);

    void detachScreen();
}
