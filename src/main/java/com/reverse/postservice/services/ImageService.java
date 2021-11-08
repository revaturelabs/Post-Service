package com.reverse.postservice.services;

import java.util.List;

public interface ImageService {

    public List<String> addImagesToBucket(List<String> base64Strings, Long userId);

}
