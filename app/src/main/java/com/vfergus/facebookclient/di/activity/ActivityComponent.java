package com.vfergus.facebookclient.di.activity;

import com.vfergus.facebookclient.di.activity.module.FacebookApiModule;
import com.vfergus.facebookclient.di.activity.module.ManagerActivityModule;
import com.vfergus.facebookclient.di.activity.module.PresenterActivityModule;
import com.vfergus.facebookclient.di.activity.module.RxActivityModule;
import com.vfergus.facebookclient.di.activity.module.SystemActivityModule;
import com.vfergus.facebookclient.di.fragment.FragmentComponent;
import com.vfergus.facebookclient.di.fragment.module.ManagerFragmentModule;
import com.vfergus.facebookclient.di.fragment.module.PresenterFragmentModule;
import com.vfergus.facebookclient.di.fragment.module.RxFragmentModule;
import com.vfergus.facebookclient.di.fragment.module.SystemFragmentModule;
import com.vfergus.facebookclient.view.activity.LoginActivity;
import com.vfergus.facebookclient.view.activity.PostsActivity;
import com.vfergus.facebookclient.view.activity.SplashActivity;

import dagger.Subcomponent;

@Subcomponent(modules = {
    SystemActivityModule.class,
    RxActivityModule.class,
    PresenterActivityModule.class,
    ManagerActivityModule.class,
    FacebookApiModule.class})
@ActivityScope
public interface ActivityComponent {

    FragmentComponent addFragmentComponent(
        SystemFragmentModule systemFragmentModule,
        PresenterFragmentModule presenterFragmentModule,
        RxFragmentModule rxFragmentModule,
        ManagerFragmentModule managerFragmentModule);

    void inject(LoginActivity activity);

    void inject(PostsActivity activity);

    void inject(SplashActivity activity);
}
