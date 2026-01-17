package com.oldcatlabs.blog.service;

import com.oldcatlabs.blog.entity.Post;
import com.oldcatlabs.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post getPostBySlug(String slug) {
        return postRepository
                .findFirstBySlug(slug)
                .orElse(null);
    }

    public Post createPost(Post post) {
        post.setCreatedAt(Instant.now().getEpochSecond());
        return postRepository.save(post);
    }

    public Post updatePostBySlug(String slug, Post post) {
        Post savedPost = postRepository.findFirstBySlug(slug).orElse(null);

        if (savedPost == null) return null;

        savedPost.setTitle(post.getTitle());
        savedPost.setSlug(slug);
        savedPost.setBody(post.getBody());

        return postRepository.save(savedPost);
    }

    public Boolean deletePostById(Integer id) {
        Post savedPost = postRepository.findById(id).orElse(null);

        if (savedPost == null) return false;

        postRepository.deleteById(id);
        return true;
    }

    public Post publishPostById(Integer id) {
        Post savedPost = postRepository.findById(id).orElse(null);

        if (savedPost == null) return null;

        savedPost.setPublished(true);
        savedPost.setPublishedAt(Instant.now().getEpochSecond());
        return postRepository.save(savedPost);
    }
}
