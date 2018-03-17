package com.vfergus.common.view;

import android.support.annotation.NonNull;
import android.view.View;

public interface ViewBinder {
    void bindViews();

    void bindViews(@NonNull final View source);

    void unbindViews();
}
