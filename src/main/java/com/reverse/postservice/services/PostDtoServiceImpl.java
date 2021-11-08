package com.reverse.postservice.services;

import com.reverse.postservice.models.dto.FullPost;
import com.reverse.postservice.models.dto.PostCreationDto;
import com.reverse.postservice.repositories.LikeDao;
import com.reverse.postservice.repositories.dto.CommentDtoDao;
import com.reverse.postservice.repositories.dto.FullPostDao;
import com.reverse.postservice.repositories.dto.PostCreationRepo;
import com.reverse.postservice.repositories.dto.PostImagesDtoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component(value = "PostDtoService")
public class PostDtoServiceImpl implements PostDtoService{

    private FullPostDao fullPostDao;
    private PostCreationRepo postCreationDao;
    private CommentDtoDao commentDao;
    private PostImagesDtoDao postImagesDao;
    private LikeDao likeDao;

    @Autowired
    public PostDtoServiceImpl(FullPostDao fullPostDao, PostCreationRepo postCreationDao, CommentDtoDao commentDao, PostImagesDtoDao postImagesDao, LikeDao likeDao) {
        this.fullPostDao = fullPostDao;
        this.postCreationDao = postCreationDao;
        this.commentDao = commentDao;
        this.postImagesDao = postImagesDao;
        this.likeDao = likeDao;
    }

    @Override
    public FullPost getPostById(int postId) {
        FullPost post = this.fullPostDao.findById(postId).get();

        post.setNumberOfLikes(this.likeDao.countByLikeId_PostId(postId));//countByPostId
        post.setComments(this.commentDao.findAllCommentsByPostId(postId));
        post.setImages(this.postImagesDao.findAllPostImagesByPostId(postId));

        return post;
    }

    @Override
    public void createPost(PostCreationDto post) {
        //todo: Make a image service. Extract and save images on the post.
        post.setCreated(Instant.now());
        post.setLastEdited(Instant.now());
        this.postCreationDao.save(post);
    }

    @Override
    public void updatePost(PostCreationDto post) {
        //Can't update images on post. So don't extract or save the images.
        Integer id = post.getId();
        String title = post.getTitle();
        String body = post.getBody();
        Instant edited = Instant.now();

        this.postCreationDao.updatePosts(id, title, body, edited);
    }

}
