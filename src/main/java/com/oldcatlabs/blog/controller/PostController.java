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

}
