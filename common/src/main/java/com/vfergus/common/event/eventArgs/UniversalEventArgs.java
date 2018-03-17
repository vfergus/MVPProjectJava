package com.vfergus.common.event.eventArgs;

import android.support.annotation.NonNull;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString
@Accessors(prefix = "_")
public class UniversalEventArgs<T> {

    public UniversalEventArgs(@NonNull final T object) {
        _object = object;
    }

    @Getter
    @NonNull
    private final T _object;
}