package com.vfergus.facebookclient.di;

import android.support.annotation.NonNull;

import com.vfergus.facebookclient.di.fragment.FragmentComponent;

public interface FragmentComponentProvider {
    @NonNull
    FragmentComponent getFragmentComponent();
}
