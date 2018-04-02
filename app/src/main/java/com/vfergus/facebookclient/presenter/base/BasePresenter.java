package com.vfergus.facebookclient.presenter.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

import com.vfergus.facebookclient.view.Screen;

@Accessors(prefix = "m")
public class BasePresenter<TScreen extends Screen> implements Presenter<TScreen> {

    @Override
    public void attachScreen(@NonNull final TScreen screen) {
        mScreen = screen;
    }

    @Override
    public void detachScreen() {
        mScreen = null;
        onScreenDetach();
    }

    protected void onScreenDetach() {

    }

    @Getter(AccessLevel.PROTECTED)
    @Nullable
    private TScreen mScreen;
}
