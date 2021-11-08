package com.reverse.postservice.repositories.dto;

import com.reverse.postservice.models.dto.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDtoDao extends JpaRepository<CommentDto, Integer> {
    List<CommentDto> findAllCommentsByPostId(int postId);


}
