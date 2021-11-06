package com.reverse.postservice.services;

import com.reverse.postservice.models.Comment;
import com.reverse.postservice.repositories.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
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
        comment.setCreated(Instant.now());
        commentDao.save(comment);
    }

    @Override
    public void deleteComment(int commentId) {
        commentDao.deleteById(commentId);
    }

    @Override
    public List<Comment> getAllCommentsOnPost(int postId) {
        return commentDao.getCommentsByPost_Id(postId);
    }

}
