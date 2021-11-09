package com.reverse.postservice.controllers;

import com.reverse.postservice.models.Comment;
import com.reverse.postservice.models.dto.CommentCreationDto;
import com.reverse.postservice.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The Comment Controller handles requests related to comments on posts such as adding, deleting, and retrieving.
 */
@RestController
@RequestMapping(path = "/comments")
public class CommentController {

    CommentService commentService;
    ValidationUtils validationUtils;

    @Autowired
    public CommentController(CommentService commentService, ValidationUtils validationUtils) {
        this.commentService = commentService;
        this.validationUtils = validationUtils;
    }

    /**
     * Will add a comment to a post.
     * @param comment The comment object containing details of the comment.
     * @return Represents the HTTP response.
     */
    @PostMapping(value = "/comment")
    public ResponseEntity commentOnPost(@RequestBody CommentCreationDto comment, @RequestHeader (name="Authorization") String token) {
        try {
            validationUtils.validateJwt(token);

            commentService.commentOnPost(comment);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Delete a comment from a post.
     * @param id The Id of the comment.
     * @return Represents the HTTP response.
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteComment(@PathVariable int id, @RequestHeader (name="Authorization") String token) {
        try {
            validationUtils.validateJwt(token);

            commentService.deleteComment(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieves all comments on a single post.
     * @param id The Id of the post.
     * @return Represents the HTTP response.
     */
    @GetMapping(value = "/post/{id}")
    public ResponseEntity<List<Comment>> getAllCommentsOnPost(@PathVariable int id, @RequestHeader (name="Authorization") String token) {
        try {
            validationUtils.validateJwt(token);

            List<Comment> comments = commentService.getAllCommentsOnPost(id);

            if(comments != null) {
                if(comments.size() > 0) {
                    return ResponseEntity.ok().body(comments);
                }
            }
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }
}
