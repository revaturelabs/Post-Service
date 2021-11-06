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
    List<Comment> getCommentsByPost_Id(int postId);
}
