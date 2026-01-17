package com.oldcatlabs.blog.mapper;

import com.oldcatlabs.blog.entity.Comment;
import com.oldcatlabs.blog.request.CreateCommentRequest;
import com.oldcatlabs.blog.response.CommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE =  Mappers.getMapper(CommentMapper.class);

    Comment fromCreateCommentRequest(CreateCommentRequest createCommentRequest);

    @Mapping(source = "post.id", target = "postId")
    CommentResponse toCommentResponse(Comment comment);

    List<CommentResponse> toCommentResponseList(List<Comment> comments);

}
