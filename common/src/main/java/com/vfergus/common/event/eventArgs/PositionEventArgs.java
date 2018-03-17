package com.vfergus.common.event.eventArgs;

import lombok.Getter;

public class PositionEventArgs extends EventArgs {
    public PositionEventArgs(final int position) {
        this.position = position;
    }

    @Getter
    private final int position;
}
