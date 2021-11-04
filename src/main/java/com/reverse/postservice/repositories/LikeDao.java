package com.reverse.postservice.repositories;

import com.reverse.postservice.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDao extends JpaRepository<Like, Integer> {
    @Modifying
    @Query(value = "INSERT INTO user_likes (post_id, user_id) VALUES (:post_id, :user_id)", nativeQuery = true)
    void likePost(@Param("post_id") int postId, @Param("user_id") int userId);

    @Modifying
    @Query(value = "DELETE FROM user_likes WHERE post_id= :post_id AND user_id= :user_id", nativeQuery = true)
    void unlikePost(@Param("post_id") int postId, @Param("user_id") int userId);
}
