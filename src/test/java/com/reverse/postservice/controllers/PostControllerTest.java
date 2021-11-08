package com.reverse.postservice.controllers;

import com.reverse.postservice.models.Like;
import com.reverse.postservice.models.dto.FullPost;
import com.reverse.postservice.models.dto.PostCreationDto;
import com.reverse.postservice.services.PostDtoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reverse.postservice.models.Post;
import com.reverse.postservice.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostControllerTest {

    private PostController testPostController;

    private PostService mockPostService;
    private PostDtoService mockPostDtoService;

    @BeforeEach
    public void init() {
        mockPostService = mock(PostService.class);
        mockPostDtoService = mock(PostDtoService.class);

        testPostController = new PostController(mockPostService, mockPostDtoService);
    }

    @Test
    public void createPostSucceedTest() {
        PostCreationDto post = mock(PostCreationDto.class);

        ResponseEntity response = testPostController.createPost(post);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void createPostFailTest() {
        PostCreationDto post = mock(PostCreationDto.class);

        when(testPostController.createPost(post)).thenThrow(new NullPointerException());

        ResponseEntity response = testPostController.createPost(post);
        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void getPostSucceedTest() {
        FullPost post = mock(FullPost.class);

        when(mockPostDtoService.getPostById(1)).thenReturn(post);
        ResponseEntity<FullPost> response = testPostController.getPost(1);

        assertEquals(post, response.getBody());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getPostFailTest() {
        when(mockPostDtoService.getPostById(1)).thenReturn(null);
        ResponseEntity<FullPost> response = testPostController.getPost(1);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void likePostSucceedTest() {
        Like like = mock(Like.class);

        ResponseEntity response = testPostController.likePost(like);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void likePostFailTest() {
        Like like = mock(Like.class);

        when(testPostController.likePost(like)).thenThrow(new NullPointerException());
        ResponseEntity response = testPostController.likePost(like);

        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void editPostSucceedTest() {
        PostCreationDto post = mock(PostCreationDto.class);

        ResponseEntity response = testPostController.editPost(post);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void editPostFailTest() {
        PostCreationDto post = mock(PostCreationDto.class);

        when(testPostController.editPost(post)).thenThrow(new NullPointerException());
        ResponseEntity response = testPostController.editPost(post);

        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void deletePostSucceedTest() {
        ResponseEntity response = testPostController.deletePost(1);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void deletePostFailTest() {
        when(testPostController.deletePost(1)).thenThrow(new NullPointerException());

        ResponseEntity response = testPostController.deletePost(1);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void getAllPostsSucceedTest() {
        Post post = mock(Post.class);

        when(mockPostService.getAllPosts()).thenReturn(Arrays.asList(post));
        ResponseEntity<List<Post>> response = testPostController.getAllPosts();

        assertEquals(post, response.getBody().get(0));
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getAllPostsFailTest() {
        when(testPostController.getAllPosts()).thenThrow(new NullPointerException());
        ResponseEntity<List<Post>> response = testPostController.getAllPosts();

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

}
