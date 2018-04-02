package com.vfergus.facebookclient.core.manager.account;

import android.support.annotation.NonNull;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;
import org.parceler.ParcelProperty;

@ToString()
@Parcel
@Accessors(prefix = "m")
public class AppAccount {
    @ParcelConstructor
    public AppAccount(@ParcelProperty("userName") @NonNull final String userName) {
        mUserName = userName;
    }

    @Getter
    @ParcelProperty("userName")
    @NonNull
    /*package-private*/ final String mUserName;
}