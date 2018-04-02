package com.vfergus.facebookclient.core.manager.auth;

import android.support.annotation.NonNull;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Accessors(prefix = "m")
@Parcel(Parcel.Serialization.BEAN)
public class AuthResult {

    @ParcelConstructor
    public AuthResult(@NonNull final String accountType, @NonNull final String userName) {
        mAccountType = accountType;
        mUserName = userName;
    }

    @Setter
    @Getter
    @NonNull
    private String mAccountType;

    @Getter
    @Setter
    @NonNull
    private String mUserName;
}
