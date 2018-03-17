package com.vfergus.common.event.eventArgs;

import lombok.Getter;
import lombok.ToString;

@ToString(doNotUseGetters = true)
public class IdEventArgs extends EventArgs {
    public IdEventArgs(final long id) {
        this.id = id;
    }

    @Getter
    private final long id;
}
