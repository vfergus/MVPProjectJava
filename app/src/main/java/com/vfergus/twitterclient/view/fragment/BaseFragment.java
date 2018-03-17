package com.vfergus.twitterclient.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import lombok.val;

import com.vfergus.common.view.fragment.ExtendedFragment;
import com.vfergus.twitterclient.di.ActivityComponentProvider;
import com.vfergus.twitterclient.di.FragmentComponentProvider;
import com.vfergus.twitterclient.di.activity.ActivityComponent;
import com.vfergus.twitterclient.di.fragment.FragmentComponent;
import com.vfergus.twitterclient.di.fragment.module.ManagerFragmentModule;
import com.vfergus.twitterclient.di.fragment.module.PresenterFragmentModule;
import com.vfergus.twitterclient.di.fragment.module.RxFragmentModule;
import com.vfergus.twitterclient.di.fragment.module.SystemFragmentModule;

import javax.annotation.Nonnull;

public abstract class BaseFragment extends ExtendedFragment implements FragmentComponentProvider {
    @NonNull
    public final ActivityComponent getActivityComponent() {
        final val activity = getActivity();
        if (activity instanceof ActivityComponentProvider) {
            final val componentProvider = (ActivityComponentProvider) activity;
            return componentProvider.getActivityComponent();
        } else {
            throw new IllegalStateException(
                "The activity must implement " + ActivityComponentProvider.class.getName());
        }
    }

    @NonNull
    @Override
    public FragmentComponent getFragmentComponent() {
        if (_fragmentComponent == null) {
            throw new IllegalStateException("The fragment has not yet been created.");
        }

        return _fragmentComponent;
    }

    @Override
    public void onCreate(
        @Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _fragmentComponent = createFragmentComponent();
    }

    @Nullable
    private FragmentComponent _fragmentComponent;

    @Nonnull
    private FragmentComponent createFragmentComponent() {
        return getActivityComponent().addFragmentComponent(
            new SystemFragmentModule(this),
            new PresenterFragmentModule(),
            new RxFragmentModule(this),
            new ManagerFragmentModule());
    }
}
