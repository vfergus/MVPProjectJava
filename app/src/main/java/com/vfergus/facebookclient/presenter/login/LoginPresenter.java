package com.vfergus.facebookclient.presenter.login;

import com.vfergus.facebookclient.presenter.base.Presenter;
import com.vfergus.facebookclient.view.LoginScreen;

public interface LoginPresenter extends Presenter<LoginScreen>{

    void loginWithFacebook();
}
