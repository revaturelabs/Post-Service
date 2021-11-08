package com.reverse.postservice.services;

import com.reverse.postservice.models.Comment;
import com.reverse.postservice.models.Like;
import com.reverse.postservice.models.Post;
import com.reverse.postservice.models.dto.CommentDto;
import com.reverse.postservice.models.dto.FullPost;
import com.reverse.postservice.models.dto.PostImagesDto;
import com.reverse.postservice.repositories.LikeDao;
import com.reverse.postservice.repositories.dto.CommentDtoDao;
import com.reverse.postservice.repositories.dto.FullPostDao;
import com.reverse.postservice.repositories.dto.PostCreationRepo;
import com.reverse.postservice.repositories.dto.PostImagesDtoDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class PostDtoServiceImplTest {

    private static PostDtoService postDtoService;
    private static FullPostDao fullPostDao;
    private static PostCreationRepo postCreationDao;
    private static CommentDtoDao commentDao;
    private static PostImagesDtoDao postImagesDao;
    private static LikeDao likeDao;

    @BeforeAll
    static void setUp() {
        fullPostDao = mock(FullPostDao.class);
        postCreationDao = mock(PostCreationRepo.class);
        commentDao = mock(CommentDtoDao.class);
        postImagesDao = mock(PostImagesDtoDao.class);
        likeDao = mock(LikeDao.class);

        postDtoService = new PostDtoServiceImpl(fullPostDao, postCreationDao, commentDao, postImagesDao, likeDao);

    }

    @Test
    void getPostById() {

        CommentDto comment = new CommentDto();
        List<CommentDto> commentList = new ArrayList<>();
        commentList.add(comment);

        PostImagesDto postImage = new PostImagesDto();
        List<PostImagesDto> postImagesDtoList = new ArrayList<>();
        postImagesDtoList.add(postImage);

        FullPost post = new FullPost();
        post.setNumberOfLikes(12L);
        post.setComments(commentList);
        post.setImages(postImagesDtoList);

        when(fullPostDao.findById(1)).thenReturn(java.util.Optional.of(post));
        when(likeDao.countByPostId(1)).thenReturn(12L);
        when(commentDao.findAllCommentsByPostId(1)).thenReturn(commentList);
        when(postImagesDao.findAllPostImagesByPostId(1)).thenReturn(postImagesDtoList);

        FullPost postRetrieved = postDtoService.getPostById(1);
        assertEquals(post, postRetrieved);
    }

    @Test
    void createPost() {
    }

    @Test
    void updatePost() {
    }
}