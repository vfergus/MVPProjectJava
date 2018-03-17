package com.vfergus.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import lombok.val;

import com.vfergus.common.manager.permission.CheckPermissionsResult;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtils {

    @NonNull
    public static CheckPermissionsResult checkPermissions(
        @NonNull final Activity activity, @NonNull final List<String> permissions) {
        val gratedPermissions = new ArrayList<String>();
        val deniedPermissions = new ArrayList<String>();
        val neverAskAgainPermissions = new ArrayList<String>();

        for (val permission : permissions) {
            if (isPermissionGranted(activity, permission)) {
                gratedPermissions.add(permission);
            } else {
                deniedPermissions.add(permission);
                if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                    neverAskAgainPermissions.add(permission);
                }
            }
        }

        return new CheckPermissionsResult(deniedPermissions,
                                          gratedPermissions,
                                          neverAskAgainPermissions);
    }

    public static boolean isPermissionGranted(
        @NonNull final Context context, @NonNull final String permission) {

        return ContextCompat.checkSelfPermission(context, permission) ==
               PackageManager.PERMISSION_GRANTED;
    }

    private PermissionUtils() {
    }
}
