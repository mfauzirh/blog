package com.oldcatlabs.blog.mapper;

import com.oldcatlabs.blog.entity.Post;
import com.oldcatlabs.blog.request.CreatePostRequest;
import com.oldcatlabs.blog.response.PostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    Post fromCreatePostRequest(CreatePostRequest request);

    PostResponse toPostResponse(Post post);
    List<PostResponse> toPostResponses(List<Post> posts);

}
