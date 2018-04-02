package com.vfergus.facebookclient.core.manager.navigation;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import lombok.experimental.Accessors;
import lombok.val;

import com.vfergus.common.view.activity.ObservableActivity;
import com.vfergus.facebookclient.view.activity.PostsActivity;

@Accessors(prefix = "_")
public class NavigationManagerImpl implements NavigationManager {
    public NavigationManagerImpl(
        @NonNull final ObservableActivity observableActivity) {

        mObservableActivity = observableActivity;
    }

    @Override
    public void navigateToTimeline() {
        val activity = getActivity();
        activity.startActivity(PostsActivity.getViewIntent(activity));
    }

    @NonNull
    protected final AppCompatActivity getActivity() {
        return mObservableActivity.asActivity();
    }

    @NonNull
    private final ObservableActivity mObservableActivity;
}
