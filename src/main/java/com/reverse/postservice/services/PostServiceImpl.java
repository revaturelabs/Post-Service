package com.reverse.postservice.services;

import com.reverse.postservice.models.Post;
import com.reverse.postservice.models.Like;
import com.reverse.postservice.repositories.CommentDao;
import com.reverse.postservice.repositories.LikeDao;
import com.reverse.postservice.repositories.PostDao;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service(value = "PostService")
public class PostServiceImpl implements PostService{

    PostDao postDao;
    LikeDao likeDao;
    CommentDao commentDao;

    @Autowired
    @Generated
    public PostServiceImpl(PostDao postDao, LikeDao likeDao, CommentDao commentDao) {
        this.postDao = postDao;
        this.likeDao = likeDao;
        this.commentDao = commentDao;
    }

    @Override
    public void createPost(Post post) {
        post.setCreated(Instant.now());
        post.setLastEdited(Instant.now());
        this.postDao.save(post);
    }

    @Override
    public Post getPostById(int postId) {
        Optional<Post> p = this.postDao.findById(postId);
        if(p.isPresent())
            return this.postDao.findById(postId).get();
        return null;
    }

    @Override
    public void likePost(Like like) {
        this.likeDao.save(like);
    }

    @Override
    public void updatePost(Post post) {
        post.setLastEdited(Instant.now());
        this.postDao.save(post);
    }

    @Override
    public void deletePost(int postId) {
        this.postDao.deleteById(postId);
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
