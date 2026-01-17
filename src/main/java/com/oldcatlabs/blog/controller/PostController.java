package com.oldcatlabs.blog.controller;

import com.oldcatlabs.blog.request.CreatePostRequest;
import com.oldcatlabs.blog.request.UpdatePostRequest;
import com.oldcatlabs.blog.response.PostResponse;
import com.oldcatlabs.blog.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostResponse> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{slug}")
    public PostResponse getPostBySlug(@PathVariable String slug) {
        return postService.getPostBySlug(slug);
    }

    @PostMapping
    public PostResponse createPost(@Valid @RequestBody CreatePostRequest request) {
        return postService.createPost(request);
    }

    @PutMapping("/{slug}")
    public PostResponse updatePostBySlug(@PathVariable String slug, @RequestBody UpdatePostRequest request) {
        return postService.updatePostBySlug(slug, request);
    }

    @DeleteMapping("/{id}")
    public Boolean deletePostBySlug(@PathVariable Integer id) {
        return postService.deletePostById(id);
    }

    @PostMapping("/{id}/publish")
    public PostResponse publishPostById(@PathVariable Integer id) {
        return postService.publishPostById(id);
    }

}
