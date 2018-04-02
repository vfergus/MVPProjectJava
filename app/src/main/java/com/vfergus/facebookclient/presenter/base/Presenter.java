package com.vfergus.facebookclient.presenter.base;

import android.support.annotation.NonNull;

import com.vfergus.facebookclient.view.Screen;

public interface Presenter<TScreen extends Screen> {
    void attachScreen(@NonNull TScreen screen);

    void detachScreen();
}
