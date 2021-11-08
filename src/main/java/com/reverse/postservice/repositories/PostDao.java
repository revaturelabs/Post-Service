package com.reverse.postservice.repositories;

import com.reverse.postservice.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostDao extends JpaRepository<Post, Integer> {
}
