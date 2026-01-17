package com.oldcatlabs.blog.controller;

import com.oldcatlabs.blog.entity.Post;
import com.oldcatlabs.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{slug}")
    public Post getPostBySlug(@PathVariable String slug) {
        return postService.getPostBySlug(slug);
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @PutMapping("/{slug}")
    public Post updatePostBySlug(@PathVariable String slug, @RequestBody Post post) {
        return postService.updatePostBySlug(slug, post);
    }

    @DeleteMapping("/{id}")
    public Boolean deletePostBySlug(@PathVariable Integer id) {
        return postService.deletePostById(id);
    }

    @PostMapping("/{id}/publish")
    public Post publishPostById(@PathVariable Integer id) {
        return postService.publishPostById(id);
    }

}
