package com.reverse.postservice.services;

import com.reverse.postservice.models.Comment;
import com.reverse.postservice.models.dto.CommentCreationDto;

import java.util.List;

public interface CommentService {

    void commentOnPost(CommentCreationDto comment);

    void deleteComment(int commentId);

    List<Comment> getAllCommentsOnPost(int postId);
}
