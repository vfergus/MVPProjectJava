package com.vfergus.facebookclient.di.application;

import com.vfergus.facebookclient.core.manager.account.AppAuthenticatorService;
import com.vfergus.facebookclient.di.activity.ActivityComponent;
import com.vfergus.facebookclient.di.activity.module.FacebookApiModule;
import com.vfergus.facebookclient.di.activity.module.ManagerActivityModule;
import com.vfergus.facebookclient.di.activity.module.PresenterActivityModule;
import com.vfergus.facebookclient.di.activity.module.RxActivityModule;
import com.vfergus.facebookclient.di.activity.module.SystemActivityModule;
import com.vfergus.facebookclient.di.application.module.ApiApplicationModule;
import com.vfergus.facebookclient.di.application.module.ManagerApplicationModule;
import com.vfergus.facebookclient.di.application.module.SystemApplicationModule;

import dagger.Component;

@Component(modules = {
    SystemApplicationModule.class, ManagerApplicationModule.class, ApiApplicationModule.class})
@ApplicationScope
public interface ApplicationComponent {
    ActivityComponent addActivityComponent(
        SystemActivityModule systemActivityModule,
        RxActivityModule rxActivityModule,
        PresenterActivityModule presenterActivityModule,
        ManagerActivityModule managerActivityModule,
        FacebookApiModule facebookApiModule);

    void inject(AppAuthenticatorService service);
}
