package com.vfergus.common.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import lombok.val;

import java.util.Locale;

public final class LocaleUtils {
    @Nullable
    public static String getLanguageTag(@NonNull final Locale locale) {
        final String tag;

        final val language = locale.getLanguage();
        final val region = locale.getCountry();

        if (!TextUtils.isEmpty(language)) {
            if (!TextUtils.isEmpty(region)) {
                tag = language + "-" + region;
            } else {
                tag = language;
            }
        } else {
            tag = null;
        }

        return tag;
    }

    private LocaleUtils() {
    }
}
