package com.vfergus.facebookclient.core.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeFormatUtils {

    public static final DateFormat DATE_TIME_FORMAT;

    static {

        DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ssZ", Locale.getDefault());
    }

    @NonNull
    public static DateFormat getDateTimeFormat() {
        return DATE_TIME_FORMAT;
    }

    @Nullable
    public static Date parseDate(@NonNull final String date) {
        Date parsedDate = null;

        try {
            parsedDate = getDateTimeFormat().parse(date);
        } catch (final ParseException ignored) {
            parsedDate = null;
        }

        return parsedDate;
    }
}
