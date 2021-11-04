package com.reverse.postservice.services;

import com.reverse.postservice.models.Comment;

import java.util.List;

public interface CommentService {

    void commentOnPost(Comment comment);

    void deleteComment(int commentId);

    List<Comment> getAllCommentsOnPost(int postId);
}
