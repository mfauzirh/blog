package com.oldcatlabs.blog.service;

import com.oldcatlabs.blog.entity.Comment;
import com.oldcatlabs.blog.entity.Post;
import com.oldcatlabs.blog.repository.CommentRepository;
import com.oldcatlabs.blog.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public List<Comment> getCommentsByPostSlug(String slug, Integer page, Integer limit) {
        Post post = postRepository.findFirstBySlugAndIsDeleted(slug, false)
                .orElseThrow(() -> new RuntimeException("post not found"));

        PageRequest pageRequest = PageRequest.of(page, limit);

        return commentRepository.findByPostId(post.getId(), pageRequest).getContent();
    }

    public Comment getCommentById(Integer id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Transactional
    public Comment createComment(Integer id, Comment comment) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("post not found"));

        comment.setCreatedAt(Instant.now().getEpochSecond());
        comment.setPost(post);
        comment = commentRepository.save(comment);

        post.setCommentCount(post.getCommentCount() + 1);
        postRepository.save(post);

        return comment;
    }
}
