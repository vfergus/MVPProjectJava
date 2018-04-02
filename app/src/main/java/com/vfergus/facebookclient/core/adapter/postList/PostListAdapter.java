package com.vfergus.facebookclient.core.adapter.postList;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.val;

import com.vfergus.common.recyclerView.adapter.ModifiableAdapter;
import com.vfergus.facebookclient.R;
import com.vfergus.facebookclient.api.model.Post;
import com.vfergus.facebookclient.core.adapter.postList.viewHolder.PostViewHolder;

import java.util.ArrayList;
import java.util.List;

@Accessors(prefix = "m")
public class PostListAdapter extends ModifiableAdapter<Post, PostViewHolder> {

    @Override
    public void onBindViewHolder(
        @NonNull final PostViewHolder holder, final int position) {
        val item = mItems.get(position);
        holder.bindViewHolder(item);
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(
        @NonNull final ViewGroup parent, final int viewType) {
        val view = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.fragment_posts_list_item, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    @NonNull
    @Getter(onMethod = @__(@Override))
    private List<Post> mItems = new ArrayList<>();
}
