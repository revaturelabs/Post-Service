package com.reverse.postservice.services;

import com.reverse.postservice.models.dto.FullPost;
import com.reverse.postservice.models.dto.PostCreationDto;
import org.springframework.stereotype.Service;

public interface PostDtoService {

    FullPost getPostById(int postId);

    void createPost(PostCreationDto post);

    void updatePost(PostCreationDto post);
}
