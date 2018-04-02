package com.vfergus.common.utils.tuple;

import android.support.annotation.Nullable;

import lombok.ToString;

@ToString(doNotUseGetters = true)
public final class Tuple2<T1, T2> {
    /*package-private*/ Tuple2(@Nullable final T1 $1, @Nullable final T2 $2) {
        m1 = $1;
        m2 = $2;
    }

    @Nullable
    private final T1 m1;

    @Nullable
    private final T2 m2;

    @Nullable
    public T1 get1() {
        return m1;
    }

    public T2 get2() {
        return m2;
    }
}
