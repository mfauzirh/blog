package com.oldcatlabs.blog.service;

import com.oldcatlabs.blog.entity.Comment;
import com.oldcatlabs.blog.entity.Post;
import com.oldcatlabs.blog.exception.ApiException;
import com.oldcatlabs.blog.mapper.CommentMapper;
import com.oldcatlabs.blog.repository.CommentRepository;
import com.oldcatlabs.blog.repository.PostRepository;
import com.oldcatlabs.blog.request.comment.CreateCommentRequest;
import com.oldcatlabs.blog.response.comment.CommentResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public List<CommentResponse> getCommentsByPostSlug(String slug, Integer page, Integer limit) {
        Post post = postRepository.findFirstBySlugAndIsDeleted(slug, false)
                .orElseThrow(() -> new ApiException("post not found", HttpStatus.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, limit);

        List<Comment> comments = commentRepository.findByPostId(post.getId(), pageRequest).getContent();

        return CommentMapper.INSTANCE.toCommentResponseList(comments);
    }

    public CommentResponse getCommentById(Integer id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ApiException("post not found", HttpStatus.NOT_FOUND));

        return CommentMapper.INSTANCE.toCommentResponse(comment);
    }

    @Transactional
    public CommentResponse createComment(Integer id, CreateCommentRequest createCommentRequest) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ApiException("post not found", HttpStatus.NOT_FOUND));

        Comment comment = CommentMapper.INSTANCE.fromCreateCommentRequest(createCommentRequest);

        comment.setCreatedAt(Instant.now().getEpochSecond());
        comment.setPost(post);
        comment = commentRepository.save(comment);

        post.setCommentCount(post.getCommentCount() + 1);
        postRepository.save(post);

        return CommentMapper.INSTANCE.toCommentResponse(comment);
    }
}
