package com.oldcatlabs.blog.service;

import com.oldcatlabs.blog.entity.Post;
import com.oldcatlabs.blog.mapper.PostMapper;
import com.oldcatlabs.blog.repository.PostRepository;
import com.oldcatlabs.blog.request.CreatePostRequest;
import com.oldcatlabs.blog.request.UpdatePostRequest;
import com.oldcatlabs.blog.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<PostResponse> getPosts(Integer page, Integer limit) {
        PageRequest pageRequest = PageRequest.of(page, limit);
        List<Post> posts = postRepository.findAllByIsDeleted(false, pageRequest).getContent();
        return PostMapper.INSTANCE.toPostResponses(posts);
    }

    public PostResponse getPostBySlug(String slug) {
        Post post= postRepository
                .findFirstBySlugAndIsDeleted(slug, false)
                .orElse(null);

        return PostMapper.INSTANCE.toPostResponse(post);
    }

    public PostResponse createPost(CreatePostRequest request) {
        Post post = PostMapper.INSTANCE.fromCreatePostRequest(request);

        post.setCreatedAt(Instant.now().getEpochSecond());
        post = postRepository.save(post);

        return PostMapper.INSTANCE.toPostResponse(post);
    }

    public PostResponse updatePostBySlug(String slug, UpdatePostRequest request) {
        Post savedPost = postRepository.findFirstBySlugAndIsDeleted(slug, false).orElse(null);

        if (savedPost == null) return null;

        savedPost.setTitle(request.getTitle());
        savedPost.setSlug(request.getSlug());
        savedPost.setBody(request.getBody());
        savedPost.setUpdatedAt(Instant.now().getEpochSecond());

        savedPost = postRepository.save(savedPost);

        return PostMapper.INSTANCE.toPostResponse(savedPost);
    }

    public Boolean deletePostById(Integer id) {

        Post savedPost = postRepository.findById(id).orElse(null);
        if (savedPost == null) return false;

        savedPost.setDeleted(true);
        savedPost.setUpdatedAt(Instant.now().getEpochSecond());
        postRepository.save(savedPost);
        return true;
    }

    public PostResponse publishPostById(Integer id) {
        Post savedPost = postRepository.findById(id).orElse(null);

        if (savedPost == null) return null;

        savedPost.setPublished(true);
        savedPost.setPublishedAt(Instant.now().getEpochSecond());
        savedPost.setUpdatedAt(Instant.now().getEpochSecond());
        savedPost = postRepository.save(savedPost);

        return PostMapper.INSTANCE.toPostResponse(savedPost);
    }
}
