package com.reverse.postservice.services;

import com.reverse.postservice.models.dto.FullPost;
import com.reverse.postservice.models.dto.PostCreationDto;

public interface PostDtoService {

    FullPost getPostById(int postId);

    void createPost(PostCreationDto post);
}
