package com.vfergus.common.recyclerView.viewHolder;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import lombok.Getter;
import lombok.experimental.Accessors;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import com.vfergus.common.view.ViewBinder;

@Accessors(prefix = "m")
public class RecyclerViewHolder extends RecyclerView.ViewHolder implements ViewBinder {
    public RecyclerViewHolder(@NonNull final View itemView) {
        super(itemView);
        bindViews(itemView);
    }

    @NonNull
    public final Context getContext() {
        return this.itemView.getContext();
    }

    @CallSuper
    @Override
    public void bindViews() {
        unbindViews();

        this.mUnbinder = ButterKnife.bind(this, this.itemView);
    }

    @CallSuper
    @Override
    public void bindViews(@NonNull final View source) {
        unbindViews();

        this.mUnbinder = ButterKnife.bind(this, source);
    }

    @CallSuper
    @Override
    public void unbindViews() {
        if (this.mUnbinder != null) {
            this.mUnbinder.unbind();
        }
    }

    @Getter
    private Unbinder mUnbinder;
}