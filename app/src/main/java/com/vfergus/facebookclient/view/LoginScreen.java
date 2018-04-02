package com.vfergus.facebookclient.view;

import android.support.annotation.NonNull;

import com.vfergus.facebookclient.core.manager.auth.AuthResult;

public interface LoginScreen extends Screen {

    void displaySuccessfully(@NonNull AuthResult authResult);
}
