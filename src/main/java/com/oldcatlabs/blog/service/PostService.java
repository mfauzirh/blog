package com.oldcatlabs.blog.service;

import com.oldcatlabs.blog.entity.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PostService {

    Post post1 = new Post(1, "title1", "slug1");
    Post post2 = new Post(2, "title2", "slug2");
    List<Post> posts = new ArrayList<>(Arrays.asList(post1, post2));

    public List<Post> getPosts() {
        return posts;
    }

    public Post getPostBySlug(String slug) {
        return posts.stream()
                .filter(p -> p.getSlug().equals(slug))
                .findFirst()
                .orElse(null);
    }

    public Post createPost(Post post) {
        posts.add(post);
        return post;
    }

    public Post updatePostBySlug(String slug, Post post) {
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

    public Boolean deletePostBySlug(Integer id) {
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

    public Post publishPostById(Integer id) {
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
