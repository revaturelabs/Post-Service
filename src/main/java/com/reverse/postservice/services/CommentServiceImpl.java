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

    /**
     * Post a comment to a post.
     * @param comment Comment to be posted.
     */
    @Override
    public void commentOnPost(CommentCreationDto comment) {
        comment.setCreated(Instant.now());
        this.commentCreationRepo.save(comment);
    }

    /**
     * Delete a comment from a post.
     * @param commentId Comment to be deleted (by Id).
     */
    @Override
    public void deleteComment(int commentId) {
        commentDao.deleteById(commentId);
    }

    /**
     * Retrieve all comments from a post.
     * @param postId Id of the post to retrieve comments from.
     * @return A list of comments from the specified post.
     */
    @Override
    public List<Comment> getAllCommentsOnPost(int postId) {
        return commentDao.getCommentsByPost_Id(postId);
    }

}
