package com.vfergus.facebookclient.view;

import com.vfergus.facebookclient.api.model.Post;

import java.util.List;

public interface PostsScreen extends Screen {
    void displayEmptyResult();

    void displayLoading();

    void displayPosts(List<Post> posts);
}
