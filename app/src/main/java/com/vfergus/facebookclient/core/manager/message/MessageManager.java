package com.vfergus.facebookclient.core.manager.message;

import android.support.annotation.NonNull;

public interface MessageManager {
    void displayMessage(int resMessage);

    void displayMessage(@NonNull String message);

    void showErrorMessage(int resMessage);

    void showErrorMessage(@NonNull String message);
}
