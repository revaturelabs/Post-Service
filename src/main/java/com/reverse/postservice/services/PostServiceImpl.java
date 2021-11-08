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

    /**
     * Create a new post.
     * @param post Post to be created.
     */
    @Override
    public void createPost(Post post) {
        post.setCreated(Instant.now());
        post.setLastEdited(Instant.now());
        this.postDao.save(post);
    }

    /**
     * Retrieve a specific post by Id.
     * @param postId Post Id of the post to retrieve.
     * @return The specified post retrieved by Id.
     */
    @Override
    public Post getPostById(int postId) {
        Optional<Post> p = this.postDao.findById(postId);
        if(p.isPresent())
            return this.postDao.findById(postId).get();
        return null;
    }

    /**
     * Like a post.
     * @param like A Like object which contains the post Id to be liked and the user Id of the user who liked the post.
     */
    @Override
    public void likePost(Like like) {
        this.likeDao.save(like);
    }

    /**
     * Update a post with new information.
     * @param post New post information.
     */
    @Override
    public void updatePost(Post post) {
        post.setLastEdited(Instant.now());
        this.postDao.save(post);
    }

    /**
     * Delete a post by Id.
     * @param postId Id of the post to delete.
     */
    @Override
    public void deletePost(int postId) {
        this.postDao.deleteById(postId);
    }

    /**
     * Retrieve all posts from the database.
     * @return A list of posts retrieved.
     */
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


    /**
     * Get the most recently created posts.
     * @param days Time frame of how old the posts must be to be included.
     * @return A list of posts.
     */
    public List<Post> getRecent(int days){
        //172,800,000 is the number of milliseconds in 48 hours
        return null;//postDao.getPostsByCreatedAfterOrderByCreated(Instant.ofEpochMilli(System.currentTimeMillis() - daysToMilliseconds(days)));
    }

    private Long daysToMilliseconds(int days){
        return (long) days * 86400000;
    }
}
