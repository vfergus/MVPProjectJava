package com.vfergus.common.recyclerView.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import lombok.val;

import com.vfergus.common.recyclerView.viewHolder.RecyclerViewHolder;

import java.util.Collection;
import java.util.List;

public abstract class ModifiableAdapter<TItem, TViewHolder extends RecyclerViewHolder>
    extends RecyclerView.Adapter<TViewHolder> {

    private static int mViewTypeIndexer = 1;

    protected static int newViewType() {
        return ++mViewTypeIndexer;
    }

    public final void addItem(final int position, @NonNull final TItem newItem) {
        addItem(position, newItem, true);
    }

    public final void addItem(
        final int position, @NonNull final TItem newItem, final boolean notify) {

        final val items = getItems();

        items.add(position, newItem);

        if (notify) {
            notifyItemInserted(position);
        }
    }

    public final void addItem(@NonNull final TItem newItem) {
        addItem(newItem, true);
    }

    public final void addItem(@NonNull final TItem newItem, final boolean notify) {
        addItem(getItemCount(), newItem, notify);
    }

    public final void addItems(@NonNull final Collection<TItem> newItems) {
        addItems(newItems, true);
    }

    public final void addItems(@NonNull final Collection<TItem> newItems, final boolean notify) {
        addItems(getItemCount(), newItems, notify);
    }

    public final void addItems(final int position, @NonNull final Collection<TItem> newItems) {
        addItems(position, newItems, true);
    }

    public final void addItems(
        final int position, @NonNull final Collection<TItem> newItems, final boolean notify) {
        final val items = getItems();

        items.addAll(position, newItems);

        if (notify) {
            notifyItemRangeInserted(position, newItems.size());
        }
    }

    public final void changeItems(@NonNull final Collection<TItem> newItems) {
        setItems(newItems, true);
    }

    public final void changeItems(@NonNull final Collection<TItem> newItems, final boolean notify) {
        final val items = getItems();

        final int itemsCount = items.size();
        final int newItemsCount = newItems.size();

        final int changedCount = Math.min(itemsCount, newItemsCount);
        final int removedCount = Math.max(0, itemsCount - changedCount);
        final int insertedCount = Math.max(0, newItemsCount - changedCount);

        items.clear();
        items.addAll(newItems);

        if (notify) {
            if (changedCount > 0) {
                notifyItemRangeChanged(0, changedCount);
            }
            if (removedCount > 0) {
                notifyItemRangeRemoved(changedCount, removedCount);
            }
            if (insertedCount > 0) {
                notifyItemRangeInserted(changedCount, insertedCount);
            }
        }
    }

    public final void removeItem(final int position) {
        removeItem(position, true);
    }

    public final void removeItem(final int position, final boolean notify) {
        final val items = getItems();

        items.remove(position);

        if (notify) {
            notifyItemRemoved(position);
        }
    }

    public final void removeItems(final boolean notify) {
        final val items = getItems();

        final int itemsCount = items.size();

        items.clear();

        if (notify) {
            notifyItemRangeRemoved(0, itemsCount);
        }
    }

    public final void removeItems() {
        removeItems(true);
    }

    public final void setItem(final int position, @NonNull final TItem newItem) {
        setItem(position, newItem, true);
    }

    public final void setItem(
        final int position, @NonNull final TItem newItem, final boolean notify) {
        final val items = getItems();

        items.set(position, newItem);

        if (notify) {
            notifyItemChanged(position);
        }
    }

    public final void setItems(@NonNull final Collection<TItem> newItems, final boolean notify) {
        final val items = getItems();

        items.clear();
        items.addAll(newItems);

        if (notify) {
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public TViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        throw new IllegalArgumentException("Unknown view type: " + viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull final TViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return getItems().size();
    }

    @NonNull
    public abstract List<TItem> getItems();

    public final void setItems(@NonNull final Collection<TItem> newItems) {
        setItems(newItems, true);
    }
}
