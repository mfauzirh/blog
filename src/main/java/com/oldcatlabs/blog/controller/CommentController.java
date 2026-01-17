package com.oldcatlabs.blog.controller;

import com.oldcatlabs.blog.request.CreateCommentRequest;
import com.oldcatlabs.blog.response.CommentResponse;
import com.oldcatlabs.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/posts/{slug}/comments")
    public List<CommentResponse> getCommentsByPostSlug(
            @PathVariable String slug,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer limit) {

        return commentService.getCommentsByPostSlug(slug, page, limit);
    }

    @GetMapping("/comments/{id}")
    public CommentResponse getCommentById(@PathVariable Integer id) {
        return commentService.getCommentById(id);
    }

    @PostMapping("/posts/{id}/comments")
    public CommentResponse createComment(@PathVariable Integer id, @RequestBody CreateCommentRequest request) {
        return commentService.createComment(id, request);
    }

}
