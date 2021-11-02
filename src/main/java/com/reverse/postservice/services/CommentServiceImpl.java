package com.reverse.postservice.services;

import com.reverse.postservice.models.Comment;
import com.reverse.postservice.repositories.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component("CommentService")
public class CommentServiceImpl implements CommentService{

    CommentDao commentDao;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public void commentOnPost(Comment comment) {
        int postId = comment.getPostId();
        int userId = comment.getUserId();
        String message = comment.getMessage();
        Timestamp created = new Timestamp(System.currentTimeMillis());
        commentDao.postComment(postId, userId, message, created);
    }

    @Override
    public void deleteComment(int commentId) {
        commentDao.deleteComment(commentId);
    }

    @Override
    public List<Comment> getAllCommentsOnPost(int postId) {
        return commentDao.getAllCommentsOnPost(postId);
    }

}
