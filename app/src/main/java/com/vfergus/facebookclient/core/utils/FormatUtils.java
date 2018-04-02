package com.vfergus.facebookclient.core.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import lombok.val;

import com.vfergus.facebookclient.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class FormatUtils {

    @Nullable
    public static String getFormattedDate(
        @NonNull final Context context, @NonNull final String time) {
        final String dateFormatted;
        val date = DateTimeFormatUtils.parseDate(time);

        if (date != null) {
            val dateCalendar = Calendar.getInstance();
            dateCalendar.setTime(date);

            val currentCalendar = Calendar.getInstance();
            currentCalendar.setTime(new Date());
            final TimeZone timeZone = TimeZone.getDefault();
            currentCalendar.setTimeZone(timeZone);

            val dateFormat = context.getString(R.string.updated_date_format_large);
            val sdf = new SimpleDateFormat(dateFormat, Locale.getDefault());

            if (currentCalendar.get(Calendar.YEAR) == dateCalendar.get(Calendar.YEAR)) {
                final int dayDiv = currentCalendar.get(Calendar.DAY_OF_YEAR) -
                                   dateCalendar.get(Calendar.DAY_OF_YEAR);
                if (dayDiv >= 1) {
                    dateFormatted = sdf.format(date);
                } else {
                    final long lastLoginInMillis = dateCalendar.getTime().getTime();
                    final long millisDiv = currentCalendar.getTimeInMillis() - lastLoginInMillis;
                    if (millisDiv < TimeUnit.MINUTES.toMillis(2)) {
                        dateFormatted = context.getString(R.string.updated_date_format_now);
                    } else if (millisDiv >= TimeUnit.HOURS.toMillis(1)) {
                        final int hourCount = Math.round(millisDiv / TimeUnit.HOURS.toMillis(1));
                        dateFormatted =
                            String.format(context.getString(R.string.updated_date_format_hours_ago),
                                          hourCount);
                    } else {
                        final int minuteCount =
                            Math.round(millisDiv / TimeUnit.MINUTES.toMillis(1));
                        dateFormatted =
                            String.format(context.getString(R.string.updated_date_format_minutes_ago,
                                          minuteCount));
                    }
                }
            } else {
                dateFormatted = sdf.format(date);
            }
        } else {
            dateFormatted = null;
        }

        return dateFormatted;
    }
}
