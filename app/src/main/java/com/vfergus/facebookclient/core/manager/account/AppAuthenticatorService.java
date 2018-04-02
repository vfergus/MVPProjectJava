package com.vfergus.facebookclient.core.manager.account;

import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class AppAuthenticatorService extends BaseService {
    @Nullable
    @Override
    public IBinder onBind(final Intent intent) {
        IBinder binder = null;
        if (_authenticator != null) {
            binder = _authenticator.getIBinder();
        }
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getApplicationComponent().inject(this);

        _authenticator = new AppAccountAuthenticator(getApplicationContext());
    }

    @Nullable
    private AppAccountAuthenticator _authenticator;
}
