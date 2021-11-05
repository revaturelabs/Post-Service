package com.reverse.postservice.controllers;

import com.reverse.postservice.models.Post;
import com.reverse.postservice.models.Like;
import com.reverse.postservice.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
* TODO:
*  When front-end retrieves a post, we need to return:
*       -A list of user IDs who liked the post.
*       -All comments on the post.
*       -All replies to each comment.
*
* TODO:
*  Add partial loading when retrieving a feed of posts.
*       -We should have it load in increments of 5, and continue to load in increments of 5
*        when a "load more" button is pressed.
* */

@RestController
@RequestMapping(path = "/posts") //This being posts syncs with the current implementation of the gateway
public class PostController {

    PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity createPost(@RequestBody Post post) {
        try {
            postService.createPost(post);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> getPost(@PathVariable int id) {
        Post post = this.postService.getPostById(id);

        if(post != null) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/like")
    public ResponseEntity likePost(@RequestBody Like like) {
        try {
            postService.likePost(like);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/edit")
    public ResponseEntity editPost(@RequestBody Post post) {
        try {
            postService.updatePost(post);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deletePost(@PathVariable int id) {
        try {
            postService.deletePost(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity getAllPosts(){
        try {
            postService.getAllPosts();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
