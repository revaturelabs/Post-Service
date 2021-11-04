package com.reverse.postservice.services;

import com.reverse.postservice.models.Like;
import com.reverse.postservice.models.LikeId;
import com.reverse.postservice.models.Post;
import com.reverse.postservice.models.User;
import com.reverse.postservice.repositories.CommentDao;
import com.reverse.postservice.repositories.LikeDao;
import com.reverse.postservice.repositories.PostDao;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class PostServiceTest {
    private PostService testPostService;

    @BeforeEach
    public void init() {
        PostDao mockPostDao = mock(PostDao.class);
        CommentDao mockCommentDao = mock(CommentDao.class);
        LikeDao mockLikeDao = mock(LikeDao.class);
        testPostService = new PostServiceImpl(mockPostDao, mockLikeDao, mockCommentDao);
    }

    @Test
    void createPostTest(){
        Post testPost = new Post();
        testPost.setTitle("Test");
        testPost.setBody("This is a test of the post creation system");

        User testUser = new User();
        testUser.setId(1);
        testPost.setPoster(testUser);

        testPostService.createPost(testPost);
    }

    @Test
    void getPostByIdTest(){
        testPostService.getPostById(1);
    }

    @Test
    void likePostTest(){
        Like like = new Like();

        LikeId likeId = new LikeId(1,1);
        like.setLikeId(likeId);

        testPostService.likePost(like);
    }

    @Test
    void updatePostTest(){
        Post testPost = new Post();
        testPost.setId(1);
        testPost.setTitle("Test");
        testPost.setBody("This is a test of the post update system");
        testPost.setLastEdited(Instant.now());

        testPostService.updatePost(testPost);
    }

    @Test
    void deletePostTest(){
        testPostService.deletePost(1);
    }

    @Test
    void getAllPostsTest(){
        testPostService.getAllPosts();
    }

    @Test
    void getFeedTest(){
        testPostService.getPostFeed(1);
    }
}
