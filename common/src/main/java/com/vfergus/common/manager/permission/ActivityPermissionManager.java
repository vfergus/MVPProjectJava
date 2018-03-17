package com.vfergus.common.manager.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import lombok.val;

import rx.Observable;
import rx.Subscriber;

import com.vfergus.common.event.eventArgs.PermissionResultEventArgs;
import com.vfergus.common.event.generic.EventHandler;
import com.vfergus.common.utils.PermissionUtils;
import com.vfergus.common.view.activity.ObservableActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

public class ActivityPermissionManager implements PermissionManager {

    private static final int REQUEST_CODE = 23;

    public ActivityPermissionManager(@Nonnull final ObservableActivity activity) {
        _activity = activity;
        _activity.getPermissionResultEvent().addHandler(_permissionResultHandler);
    }

    @NonNull
    @Override
    public CheckPermissionsResult checkPermissions(
        @NonNull final List<String> permissions) {
        return PermissionUtils.checkPermissions((Activity) _activity, permissions);
    }

    @NonNull
    @Override
    public Observable<RequestPermissionsResult> createPermissionsRequest(
        @NonNull final List<String> permissions) {

        return Observable.unsafeCreate(new Observable.OnSubscribe<RequestPermissionsResult>() {
            @Override
            public void call(final Subscriber<? super RequestPermissionsResult> subscriber) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    _subscriber = subscriber;
                    val activity = (Activity) _activity;
                    activity.requestPermissions((String[]) permissions.toArray(), REQUEST_CODE);
                } else {
                    subscriber.onNext(new RequestPermissionsResult(Collections.<String>emptyList(),
                                                                   permissions));
                    subscriber.onCompleted();
                }
            }
        });
    }

    @Nonnull
    private final ObservableActivity _activity;

    @Nullable
    private Subscriber<? super RequestPermissionsResult> _subscriber;

    @Nonnull
    private final EventHandler<PermissionResultEventArgs> _permissionResultHandler =
        new EventHandler<PermissionResultEventArgs>() {
            @Override
            public void onEvent(
                @NonNull final PermissionResultEventArgs eventArgs) {

                if (_subscriber != null) {
                    val permissions = eventArgs.getPermissions();
                    val granted = eventArgs.getGrantResults();
                    val deniedPermissions = new ArrayList<String>();
                    val grantedPermissions = new ArrayList<String>();
                    for (int i = 0; i < permissions.length; i++) {
                        if (granted[i] == PackageManager.PERMISSION_GRANTED) {
                            grantedPermissions.add(permissions[i]);
                        } else {
                            deniedPermissions.add(permissions[i]);
                        }
                    }
                    _subscriber.onNext(new RequestPermissionsResult(deniedPermissions,
                                                                    grantedPermissions));
                    _subscriber.onCompleted();
                }
            }
        };
}
