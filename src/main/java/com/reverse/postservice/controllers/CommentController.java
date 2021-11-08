package com.reverse.postservice.controllers;

import com.reverse.postservice.models.Comment;
import com.reverse.postservice.models.dto.CommentCreationDto;
import com.reverse.postservice.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping(value = "/comment")
    public ResponseEntity commentOnPost(@RequestBody CommentCreationDto comment, @RequestHeader (name="Authorization") String token) {
        try {
            validationUtils.validateJwt(token);

            commentService.commentOnPost(comment);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

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
