package com.vfergus.common.utils.tuple;

import android.support.annotation.Nullable;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString(doNotUseGetters = true)
@Accessors(prefix = "m")
public final class Tuple1<T1> {
    /*package-private*/ Tuple1(@Nullable final T1 $1) {
        m1 = $1;
    }

    @Getter
    @Nullable
    private final T1 m1;
}
