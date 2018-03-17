package com.vfergus.twitterclient.di.fragment;

import com.vfergus.twitterclient.di.fragment.module.ManagerFragmentModule;
import com.vfergus.twitterclient.di.fragment.module.PresenterFragmentModule;
import com.vfergus.twitterclient.di.fragment.module.RxFragmentModule;
import com.vfergus.twitterclient.di.fragment.module.SystemFragmentModule;

import dagger.Subcomponent;

@Subcomponent(modules = {
    SystemFragmentModule.class,
    PresenterFragmentModule.class,
    RxFragmentModule.class,
    ManagerFragmentModule.class})
@FragmentScope
public interface FragmentComponent {
}
