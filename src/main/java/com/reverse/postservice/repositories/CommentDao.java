package com.reverse.postservice.repositories;

import com.reverse.postservice.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CommentDao extends JpaRepository<Comment, Integer> {

    @Modifying
    @Query(value = "INSERT INTO post_comments\n" +
            "(post_id, commenter_id, message, created)\n" +
            "VALUES(:postId, :userId, :message, :created)", nativeQuery = true)
    void postComment(@Param("postId") int postId, @Param("userId") int userId, @Param("message") String message, @Param("created") Timestamp createdDate);

    @Modifying
    @Query(value = "DELETE FROM post_comments\n" +
            "WHERE id= :id", nativeQuery = true)
    void deleteComment(@Param("id") int postId);

    @Query(value = "SELECT * FROM post_comments where id = :id", nativeQuery = true)
    List<Comment> getAllCommentsOnPost(@Param("id") int postId);
}
