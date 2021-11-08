package com.reverse.postservice.services;

import com.reverse.postservice.models.Comment;
import com.reverse.postservice.models.dto.CommentCreationDto;
import com.reverse.postservice.repositories.CommentDao;
import com.reverse.postservice.repositories.dto.CommentCreationRepo;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service("CommentService")
public class CommentServiceImpl implements CommentService{

    private CommentDao commentDao;
    private CommentCreationRepo commentCreationRepo;

    @Autowired
    @Generated
    public CommentServiceImpl(CommentDao commentDao, CommentCreationRepo commentCreationRepo) {
        this.commentDao = commentDao;
        this.commentCreationRepo = commentCreationRepo;
    }

    @Override
    public void commentOnPost(CommentCreationDto comment) {
        comment.setCreated(Instant.now());
        this.commentCreationRepo.save(comment);
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
