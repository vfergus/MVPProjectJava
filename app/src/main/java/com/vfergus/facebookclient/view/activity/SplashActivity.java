package com.vfergus.facebookclient.view.activity;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import com.vfergus.facebookclient.R;
import com.vfergus.facebookclient.di.qualifier.PresenterNames;
import com.vfergus.facebookclient.presenter.splash.SplashPresenter;
import com.vfergus.facebookclient.view.Screen;

import javax.inject.Inject;
import javax.inject.Named;

public class SplashActivity extends BaseActivity implements Screen {

    @Override
    protected void onResume() {
        super.onResume();

        if (mPresenter != null) {
            mPresenter.checkAccounts();
        }
    }

    @CallSuper
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getActivityComponent().inject(this);
        if (mPresenter != null) {
            mPresenter.attachScreen(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachScreen();
        }
        super.onDestroy();
    }

    @Named(PresenterNames.SPLASH)
    @Inject
    @Nullable
    SplashPresenter mPresenter;
}
