package com.reverse.postservice.services;

import com.reverse.postservice.models.dto.FullPost;

public interface PostDtoService {

    FullPost getPostById(int postId);
}
