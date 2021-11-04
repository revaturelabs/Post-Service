package com.reverse.postservice.services;

import com.reverse.postservice.models.Post;
import com.reverse.postservice.models.Like;
import com.reverse.postservice.repositories.CommentDao;
import com.reverse.postservice.repositories.LikeDao;
import com.reverse.postservice.repositories.PostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Component(value = "PostService")
public class PostServiceImpl implements PostService{

    PostDao postDao;
    LikeDao likeDao;
    CommentDao commentDao;

    @Autowired
    public PostServiceImpl(PostDao postDao, LikeDao likeDao, CommentDao commentDao) {
        this.postDao = postDao;
        this.likeDao = likeDao;
        this.commentDao = commentDao;
    }

    @Override
    public void createPost(Post post) {
        Integer posterId = post.getPoster().getId();
        String title = post.getTitle();
        String body = post.getBody();
        Timestamp creationDate = new Timestamp(System.currentTimeMillis());
        Timestamp lastEditedDate = new Timestamp(System.currentTimeMillis());
        this.postDao.createPost(posterId, title, body, creationDate, lastEditedDate);
    }

    @Override
    public Post getPostById(int postId) {
        return this.postDao.getPostById(postId);
    }

    @Override
    public void likePost(Like like) {

    }

    @Override
    public void updatePost(Post post) {
        Integer postId = post.getId();

        if(postId != null) {
            String title = post.getTitle();
            String body = post.getBody();
            Instant lastEditedDate = Instant.now();
            this.postDao.updatePost(postId, title, body, lastEditedDate);
        }
    }

    @Override
    public void deletePost(int postId) {
        this.postDao.deletePost(postId);
    }

    @Override
    public List<Post> getAllPosts(){
        return postDao.findAll();
    }

    /**
     * Gets a personalized set of posts for a specific user
     * @param userId the ID of the user whose experience is being personalized
     * @return a list of posts customized based on some criteria
     */
    @Override
    public List<Post> getPostFeed(int userId){
        return getAllPosts();
    }
}
