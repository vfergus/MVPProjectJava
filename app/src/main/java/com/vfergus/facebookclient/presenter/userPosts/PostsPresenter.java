package com.vfergus.facebookclient.presenter.userPosts;

import com.vfergus.facebookclient.presenter.base.Presenter;
import com.vfergus.facebookclient.view.PostsScreen;

public interface PostsPresenter extends Presenter<PostsScreen>{

    void loadPosts();
}
