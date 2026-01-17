package com.oldcatlabs.blog.service;

import com.oldcatlabs.blog.entity.Post;
import com.oldcatlabs.blog.repository.PostRepository;
import com.oldcatlabs.blog.request.CreatePostRequest;
import com.oldcatlabs.blog.response.CreatePostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getPosts() {
        return postRepository.findAllByIsDeleted(false);
    }

    public Post getPostBySlug(String slug) {
        return postRepository
                .findFirstBySlugAndIsDeleted(slug, false)
                .orElse(null);
    }

    public CreatePostResponse createPost(CreatePostRequest request) {
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setBody(request.getBody());
        post.setSlug(request.getSlug());

        post.setCreatedAt(Instant.now().getEpochSecond());
        post = postRepository.save(post);

        CreatePostResponse response = new CreatePostResponse();
        response.setTitle(post.getTitle());
        response.setBody(post.getBody());
        response.setSlug(post.getSlug());
        response.setPublishedAt(post.getCreatedAt());
        response.setCommentCount(post.getCommentCount());

        return response;
    }

    public Post updatePostBySlug(String slug, Post post) {
        Post savedPost = postRepository.findFirstBySlugAndIsDeleted(slug, false).orElse(null);

        if (savedPost == null) return null;

        savedPost.setTitle(post.getTitle());
        savedPost.setSlug(slug);
        savedPost.setBody(post.getBody());
        savedPost.setUpdatedAt(Instant.now().getEpochSecond());

        return postRepository.save(savedPost);
    }

    public Boolean deletePostById(Integer id) {

        Post savedPost = postRepository.findById(id).orElse(null);
        if (savedPost == null) return false;

        savedPost.setDeleted(true);
        savedPost.setUpdatedAt(Instant.now().getEpochSecond());
        postRepository.save(savedPost);
        return true;
    }

    public Post publishPostById(Integer id) {
        Post savedPost = postRepository.findById(id).orElse(null);

        if (savedPost == null) return null;

        savedPost.setPublished(true);
        savedPost.setPublishedAt(Instant.now().getEpochSecond());
        savedPost.setUpdatedAt(Instant.now().getEpochSecond());
        return postRepository.save(savedPost);
    }
}
