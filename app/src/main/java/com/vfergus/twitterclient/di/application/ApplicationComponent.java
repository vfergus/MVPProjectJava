package com.vfergus.twitterclient.di.application;

import com.vfergus.twitterclient.di.activity.ActivityComponent;
import com.vfergus.twitterclient.di.activity.module.ManagerActivityModule;
import com.vfergus.twitterclient.di.activity.module.PresenterActivityModule;
import com.vfergus.twitterclient.di.activity.module.RxActivityModule;
import com.vfergus.twitterclient.di.activity.module.SystemActivityModule;
import com.vfergus.twitterclient.di.application.module.ApiApplicationModule;
import com.vfergus.twitterclient.di.application.module.ManagerApplicationModule;
import com.vfergus.twitterclient.di.application.module.SystemApplicationModule;

import dagger.Component;

@Component(modules = {
    SystemApplicationModule.class, ManagerApplicationModule.class, ApiApplicationModule.class})
@ApplicationScope
public interface ApplicationComponent {
    ActivityComponent addActivityComponent(
        SystemActivityModule systemActivityModule,
        RxActivityModule rxActivityModule,
        PresenterActivityModule presenterActivityModule,
        ManagerActivityModule managerActivityModule);
}
