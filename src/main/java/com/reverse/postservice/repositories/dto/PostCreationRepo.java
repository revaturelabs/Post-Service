package com.reverse.postservice.repositories.dto;

import com.reverse.postservice.models.dto.PostCreationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface PostCreationRepo extends JpaRepository<PostCreationDto, Integer> {

//    @Modifying
//    @Query(name = "UPDATE posts SET poster_id= :posterId, title= :title, body= :body, last_edited= :edited WHERE id= :id", nativeQuery = true)
//    void updatePost(@Param("id")int id, @Param("posterId")int posterId, @Param("title")String title, @Param("body")String body, @Param("edited")Instant edited);

    @Modifying
    @Query(value = "UPDATE posts SET title= :title, body= :body, last_edited= :edited WHERE id= :id", nativeQuery = true)
    void updatePosts(@Param("id") int id, @Param("title") String title, @Param("body") String body, @Param("edited") Instant edited);
}
