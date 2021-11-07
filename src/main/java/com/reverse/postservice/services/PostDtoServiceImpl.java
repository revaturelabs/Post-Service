package com.reverse.postservice.services;

import com.reverse.postservice.models.dto.FullPost;
import com.reverse.postservice.models.dto.PostCreationDto;
import com.reverse.postservice.repositories.LikeDao;
import com.reverse.postservice.repositories.dto.CommentDtoDao;
import com.reverse.postservice.repositories.dto.FullPostDao;
import com.reverse.postservice.repositories.dto.PostCreationDtoDao;
import com.reverse.postservice.repositories.dto.PostImagesDtoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "PostDtoService")
public class PostDtoServiceImpl implements PostDtoService{

    private FullPostDao fullPostDao;
    private PostCreationDtoDao postCreationDao;
    private CommentDtoDao commentDao;
    private PostImagesDtoDao postImagesDao;
    private LikeDao likeDao;

    @Autowired
    public PostDtoServiceImpl(FullPostDao fullPostDao, PostCreationDtoDao postCreationDao, CommentDtoDao commentDao, PostImagesDtoDao postImagesDao, LikeDao likeDao) {
        this.fullPostDao = fullPostDao;
        this.postCreationDao = postCreationDao;
        this.commentDao = commentDao;
        this.postImagesDao = postImagesDao;
        this.likeDao = likeDao;
    }

    @Override
    public FullPost getPostById(int postId) {
        FullPost post = this.fullPostDao.findById(postId).get();

        post.setNumberOfLikes(this.likeDao.countByPostId(postId));
        post.setComments(this.commentDao.findAllCommentsByPostId(postId));
        post.setImages(this.postImagesDao.findAllPostImagesByPostId(postId));

        return post;
    }

    @Override
    public void createPost(PostCreationDto post) {
        this.postCreationDao.save(post);
    }

}
