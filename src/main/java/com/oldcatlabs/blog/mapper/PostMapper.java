package com.oldcatlabs.blog.mapper;

import com.oldcatlabs.blog.entity.Post;
import com.oldcatlabs.blog.request.CreatePostRequest;
import com.oldcatlabs.blog.response.CreatePostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    Post fromCreatePostRequest(CreatePostRequest request);

    CreatePostResponse toCreatePostResponse(Post post);

}
