package com.vfergus.facebookclient.presenter.splash;

import com.vfergus.facebookclient.presenter.base.Presenter;
import com.vfergus.facebookclient.view.Screen;

public interface SplashPresenter extends Presenter<Screen> {

    void checkAccounts();
}
