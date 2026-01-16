package com.oldcatlabs.blog.controller;

import com.oldcatlabs.blog.entity.Post;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    Post post1 = new Post(1, "title1", "slug1");
    Post post2 = new Post(2, "title2", "slug2");
    List<Post> posts = new ArrayList<>(Arrays.asList(post1, post2));

    @GetMapping
    public List<Post> getPosts() {
        return posts;
    }

    @GetMapping("/{slug}")
    public Post getPostBySlug(@PathVariable String slug) {
        return posts.stream()
                .filter(p -> p.getSlug().equals(slug))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @PutMapping("/{slug}")
    public Post updatePostBySlug(@PathVariable String slug, @RequestBody Post post) {
        Post savedPost = posts.stream()
                .filter(p -> p.getSlug().equals(slug))
                .findFirst()
                .orElse(null);

        if (savedPost == null) {
            return null;
        }

        posts.remove(savedPost);

        savedPost.setTitle(post.getTitle());
        savedPost.setSlug(post.getSlug());
        posts.add(savedPost);

        return savedPost;
    }

    @DeleteMapping("/{id}")
    public Boolean deletePostBySlug(@PathVariable Integer id) {
        Post savedPost = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (savedPost == null) {
            return false;
        }

        posts.remove(savedPost);
        return true;
    }

    @PostMapping("/{id}/publish")
    public Post publishPostById(@PathVariable Integer id) {
        Post savedPost = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (savedPost == null) {
            return null;
        }

        savedPost.setPublished(true);
        return savedPost;
    }

}
