package com.vfergus.common.utils.tuple;

import android.support.annotation.Nullable;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString(doNotUseGetters = true)
@Accessors(prefix = "m")
public final class Tuple4<T1, T2, T3, T4> {
    /*package-private*/ Tuple4(
        @Nullable final T1 $1,
        @Nullable final T2 $2,
        @Nullable final T3 $3,
        @Nullable final T4 $4) {
        m1 = $1;
        m2 = $2;
        m3 = $3;
        m4 = $4;
    }

    @Getter
    @Nullable
    private final T1 m1;

    @Getter
    @Nullable
    private final T2 m2;

    @Getter
    @Nullable
    private final T3 m3;

    @Getter
    @Nullable
    private final T4 m4;
}
