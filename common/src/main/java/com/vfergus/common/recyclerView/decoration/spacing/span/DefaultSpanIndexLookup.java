package com.vfergus.common.recyclerView.decoration.spacing.span;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import lombok.val;

public class DefaultSpanIndexLookup implements SpanIndexLookup {
    @Override
    public int getSpanIndex(@NonNull final View view) {

        final int spanIndex;

        final val layoutParams = view.getLayoutParams();
        if (layoutParams instanceof GridLayoutManager.LayoutParams) {
            spanIndex = ((GridLayoutManager.LayoutParams) layoutParams).getSpanIndex();
        } else if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            spanIndex = ((StaggeredGridLayoutManager.LayoutParams) layoutParams).getSpanIndex();
        } else if (layoutParams instanceof RecyclerView.LayoutParams) {
            spanIndex = 0;
        } else {
            spanIndex = 0;
        }

        return spanIndex;
    }
}