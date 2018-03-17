package com.vfergus.twitterclient.di;

import android.support.annotation.NonNull;

import com.vfergus.twitterclient.di.fragment.FragmentComponent;

public interface FragmentComponentProvider {
    @NonNull
    FragmentComponent getFragmentComponent();
}
