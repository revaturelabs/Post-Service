package com.reverse.postservice.repositories.dto;

import com.reverse.postservice.models.dto.FullPost;
import com.reverse.postservice.models.dto.PostCreationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCreationDtoDao extends JpaRepository<PostCreationDto, Integer> {
}
