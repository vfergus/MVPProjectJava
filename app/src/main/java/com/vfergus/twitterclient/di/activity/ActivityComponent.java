package com.vfergus.twitterclient.di.activity;

import com.vfergus.twitterclient.di.activity.module.ManagerActivityModule;
import com.vfergus.twitterclient.di.activity.module.PresenterActivityModule;
import com.vfergus.twitterclient.di.activity.module.RxActivityModule;
import com.vfergus.twitterclient.di.activity.module.SystemActivityModule;
import com.vfergus.twitterclient.di.fragment.FragmentComponent;
import com.vfergus.twitterclient.di.fragment.module.ManagerFragmentModule;
import com.vfergus.twitterclient.di.fragment.module.PresenterFragmentModule;
import com.vfergus.twitterclient.di.fragment.module.RxFragmentModule;
import com.vfergus.twitterclient.di.fragment.module.SystemFragmentModule;

import dagger.Subcomponent;

@Subcomponent(modules = {
    SystemActivityModule.class,
    RxActivityModule.class,
    PresenterActivityModule.class,
    ManagerActivityModule.class})
@ActivityScope
public interface ActivityComponent {

    FragmentComponent addFragmentComponent(
        SystemFragmentModule systemFragmentModule,
        PresenterFragmentModule presenterFragmentModule,
        RxFragmentModule rxFragmentModule,
        ManagerFragmentModule managerFragmentModule);
}
