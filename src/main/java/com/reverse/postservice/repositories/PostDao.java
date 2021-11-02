package com.reverse.postservice.repositories;

import com.reverse.postservice.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface PostDao extends JpaRepository<Post, Integer> {

    @Query(value = "select * from posts where id = :id", nativeQuery = true)
    Post getPostById(@Param("id")int id);

    @Modifying
    @Query(value = "INSERT INTO posts (poster_id, title, body, created, last_edited) VALUES (:userId, :title, :body, :creationDate, :lastEditDate)", nativeQuery = true)
    void createPost(@Param("userId") int userId, @Param("title") String title, @Param("body") String body, @Param("creationDate")Timestamp created, @Param("lastEditDate") Timestamp lastEdited);

    @Modifying
    @Query(value = "UPDATE public.posts\n" +
            "SET title= :title, body= :body, last_edited= :lastEditDate\n" +
            "WHERE id= :id", nativeQuery = true)
    void updatePost(@Param("id") int id, @Param("title") String title, @Param("body") String body, @Param("lastEditDate") Timestamp lastEdited);

    @Modifying
    @Query(value = "DELETE FROM posts\n" +
            "WHERE id= :id", nativeQuery = true)
    void deletePost(@Param("id") int id);
}
