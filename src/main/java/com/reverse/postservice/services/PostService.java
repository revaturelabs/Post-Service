package com.reverse.postservice.services;

import com.reverse.postservice.models.Post;
import com.reverse.postservice.models.Like;

import java.util.List;

public interface PostService {

    void createPost(Post post);

    Post getPostById(int postId);

    void likePost(Like like);

    void updatePost(Post post);

    void deletePost(int postId);

    List<Post> getAllPosts();

    List<Post> getPostFeed(int userId);
}
