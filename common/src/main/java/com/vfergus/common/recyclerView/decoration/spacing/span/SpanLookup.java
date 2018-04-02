package com.vfergus.common.recyclerView.decoration.spacing.span;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

public interface SpanLookup {
    int getSpanCount(@NonNull RecyclerView.LayoutManager layoutManager);
}
