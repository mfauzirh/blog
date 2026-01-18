    package com.oldcatlabs.blog.mapper;

    import com.oldcatlabs.blog.entity.Comment;
    import com.oldcatlabs.blog.request.comment.CreateCommentRequest;
    import com.oldcatlabs.blog.response.comment.CommentResponse;
    import org.mapstruct.Mapper;
    import org.mapstruct.Mapping;
    import org.mapstruct.factory.Mappers;

    import java.util.List;

    @Mapper
    public interface CommentMapper {

        CommentMapper INSTANCE =  Mappers.getMapper(CommentMapper.class);

        Comment fromCreateCommentRequest(CreateCommentRequest createCommentRequest);

        @Mapping(source = "post.id", target = "post.id")
        @Mapping(source = "post.title", target = "post.title")
        @Mapping(source = "post.slug", target = "post.slug")
        CommentResponse toCommentResponse(Comment comment);

        List<CommentResponse> toCommentResponseList(List<Comment> comments);

    }
