package com.reverse.postservice.repositories;

import com.reverse.postservice.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Repository
public interface PostDao extends JpaRepository<Post, Integer> {
    List<Post> getPostsByCreatedAfterOrderByCreated(Instant firstAcceptable);
}
