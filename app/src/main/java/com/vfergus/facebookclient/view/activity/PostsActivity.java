package com.vfergus.facebookclient.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.val;

import butterknife.BindView;

import com.vfergus.common.LoadingViewDelegate;
import com.vfergus.common.recyclerView.decoration.spacing.SpacingItemDecoration;
import com.vfergus.facebookclient.R;
import com.vfergus.facebookclient.api.model.Post;
import com.vfergus.facebookclient.core.adapter.postList.PostListAdapter;
import com.vfergus.facebookclient.di.qualifier.PresenterNames;
import com.vfergus.facebookclient.presenter.userPosts.PostsPresenter;
import com.vfergus.facebookclient.view.PostsScreen;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

@Accessors(prefix = "m")
public class PostsActivity extends BaseActivity implements PostsScreen {

    public static Intent getViewIntent(@NonNull final Context context) {
        return new Intent(context, PostsActivity.class);
    }

    @Override
    public void displayEmptyResult() {
        if (mLoadingDelegate != null) {
            mLoadingDelegate.showNoContent();
        }
    }

    @Override
    public void displayLoading() {
        if (mLoadingDelegate != null) {
            mLoadingDelegate.showLoading();
        }
    }

    @Override
    public void displayPosts(
        @NonNull final List<Post> posts) {
        if (mAdapter != null) {
            mAdapter.setItems(posts);
        }
        if (mLoadingDelegate != null) {
            mLoadingDelegate.showContent();
        }
    }

    @Override
    public void onCreate(
        @Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_posts);

        bindViews();
        getActivityComponent().inject(this);
        if (mPresenter != null) {
            mPresenter.attachScreen(this);
        }

        mLoadingDelegate = LoadingViewDelegate
            .builder()
            .setLoadingView(mLoadingView)
            .setContentView(mPostListView)
            .setNoContentView(mNoContentView)
            .build();

        initPostsView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mPresenter != null) {
            mPresenter.loadPosts();
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachScreen();
        }
        super.onDestroy();
    }

    @Nullable
    @BindView(R.id.loading)
    ProgressBar mLoadingView;

    @Nullable
    @BindView(R.id.no_content)
    View mNoContentView;

    @Nullable
    @BindView(R.id.post_list)
    RecyclerView mPostListView;

    @Named(PresenterNames.POST_LIST)
    @Inject
    @Getter(AccessLevel.PROTECTED)
    @Nullable
    PostsPresenter mPresenter;

    @Nullable
    private PostListAdapter mAdapter;

    @Nullable
    private LoadingViewDelegate mLoadingDelegate;

    private void initPostsView() {
        if (mPostListView != null) {
            mAdapter = new PostListAdapter();
            mPostListView.setAdapter(mAdapter);
            mPostListView.setLayoutManager(new LinearLayoutManager(this,
                                                                   LinearLayoutManager.VERTICAL,
                                                                   false));
            val decoration = SpacingItemDecoration
                .builder()
                .setVerticalSpacing(getResources().getDimensionPixelOffset(R.dimen
                                                                               .list_item_spasing))
                .build();
            mPostListView.addItemDecoration(decoration);
        }
    }
}
