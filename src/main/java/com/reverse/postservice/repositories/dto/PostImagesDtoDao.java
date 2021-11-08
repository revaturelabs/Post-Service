package com.reverse.postservice.repositories.dto;

import com.reverse.postservice.models.dto.PostImagesDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostImagesDtoDao extends JpaRepository<PostImagesDto, Integer> {
    List<PostImagesDto> findAllPostImagesByPostId(int postId);
}
