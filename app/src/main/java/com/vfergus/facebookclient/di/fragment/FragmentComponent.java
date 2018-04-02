package com.vfergus.facebookclient.di.fragment;

import com.vfergus.facebookclient.di.fragment.module.ManagerFragmentModule;
import com.vfergus.facebookclient.di.fragment.module.PresenterFragmentModule;
import com.vfergus.facebookclient.di.fragment.module.RxFragmentModule;
import com.vfergus.facebookclient.di.fragment.module.SystemFragmentModule;

import dagger.Subcomponent;

@Subcomponent(modules = {
    SystemFragmentModule.class,
    PresenterFragmentModule.class,
    RxFragmentModule.class,
    ManagerFragmentModule.class})
@FragmentScope
public interface FragmentComponent {
}
