package com.vfergus.common.recyclerView.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.val;

import com.vfergus.common.R;
import com.vfergus.common.recyclerView.viewHolder.LoadingViewHolder;
import com.vfergus.common.recyclerView.viewHolder.RecyclerViewHolder;

@Accessors(prefix = "m")
public abstract class PaginationAdapter<TItem>
    extends ModifiableAdapter<TItem, RecyclerViewHolder> {

    public static final int VIEW_TYPE_LOADING = newViewType();

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(
        @NonNull final ViewGroup parent, final int viewType) {

        final RecyclerViewHolder holder;

        if (viewType == VIEW_TYPE_LOADING) {
            holder = onCreateLoadingViewHolder(parent, viewType);
        } else {
            holder = super.onCreateViewHolder(parent, viewType);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(
        @NonNull final RecyclerViewHolder holder, final int position) {

        final int viewType = getItemViewType(position);
        if (viewType == VIEW_TYPE_LOADING) {
            bindLoadingViewHolder((LoadingViewHolder) holder);
        } else {
            super.onBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + (isLoadingPage() ? 1 : 0);
    }

    public void setLoadingNextPage(final boolean loadingNextPage) {
        if (mLoadingPage != loadingNextPage) {
            mLoadingPage = loadingNextPage;

            onLoadingNextPageStateChanged();
        }
    }

    @NonNull
    protected RecyclerViewHolder onCreateLoadingViewHolder(
        final ViewGroup parent, final int viewType) {
        val view = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.fragment_loading_item, parent, false);
        return new LoadingViewHolder(view);
    }

    @CallSuper
    protected void onLoadingNextPageStateChanged() {
        if (isLoadingPage()) {
            notifyItemInserted(getItemCount());
        } else {
            notifyItemRemoved(getItemCount());
        }
    }

    @Getter
    private boolean mLoadingPage = false;

    private void bindLoadingViewHolder(@NonNull final LoadingViewHolder holder) {

    }
}
