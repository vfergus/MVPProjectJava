package com.vfergus.common.recyclerView.viewHolder;

import android.support.annotation.NonNull;
import android.view.View;

public abstract class ExtendedRecyclerViewHolder<T> extends RecyclerViewHolder {

    public abstract void bindViewHolder(@NonNull T item);

    protected ExtendedRecyclerViewHolder(@NonNull final View itemView) {
        super(itemView);
    }
}

