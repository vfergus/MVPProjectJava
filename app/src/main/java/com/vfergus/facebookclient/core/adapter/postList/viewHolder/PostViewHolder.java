package com.vfergus.facebookclient.core.adapter.postList.viewHolder;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import lombok.val;

import butterknife.BindView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vfergus.common.recyclerView.viewHolder.ExtendedRecyclerViewHolder;
import com.vfergus.facebookclient.R;
import com.vfergus.facebookclient.api.FacebookClientConstants;
import com.vfergus.facebookclient.api.model.Post;
import com.vfergus.facebookclient.core.utils.FormatUtils;

public class PostViewHolder extends ExtendedRecyclerViewHolder<Post> {

    public PostViewHolder(final View itemView) {
        super(itemView);
        bindViews(itemView);
    }

    @Override
    public void bindViewHolder(@NonNull final Post item) {
        val from = item.getFrom();
        val requestOptions = new RequestOptions();
        requestOptions
            .error(R.drawable.com_facebook_profile_picture_blank_square)
            .placeholder(R.drawable.com_facebook_profile_picture_blank_square)
            .fallback(R.drawable.com_facebook_profile_picture_blank_square)
            .circleCrop();
        Glide
            .with(getContext())
            .load(FacebookClientConstants.BASE_URL + from.getId() + FacebookClientConstants.PICTURE)
            .apply(requestOptions)
            .into(avatarView);
        nameView.setText(from.getName());

        val story = item.getStory();
        messageView.setText(!TextUtils.isEmpty(story) ? story : item.getMessage());
        dateView.setText(FormatUtils.getFormattedDate(getContext(), item.getUpdatedTime()));

        val photoUri = item.getPicture();
        if (!TextUtils.isEmpty(photoUri)) {
            photoView.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(photoUri).into(photoView);
        } else {
            photoView.setVisibility(View.GONE);
        }
    }

    @BindView(R.id.avatar)
    ImageView avatarView;

    @BindView(R.id.date)
    TextView dateView;

    @BindView(R.id.message)
    TextView messageView;

    @BindView(R.id.name)
    TextView nameView;

    @BindView(R.id.photo)
    ImageView photoView;
}
