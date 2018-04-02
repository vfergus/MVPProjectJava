package com.vfergus.common.recyclerView.decoration.spacing.span;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

public class DefaultSpanLookup implements SpanLookup {
    @Override
    public int getSpanCount(@NonNull final RecyclerView.LayoutManager layoutManager) {

        final int spanCount;

        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof LinearLayoutManager) {
            spanCount = 1;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        } else {
            spanCount = 1;
        }

        return spanCount;
    }
}
