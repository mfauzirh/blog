package com.oldcatlabs.blog.controller;

import com.oldcatlabs.blog.entity.Comment;
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
    public List<Comment> getCommentsByPostSlug(
            @PathVariable String slug, @RequestParam Integer page, @RequestParam Integer limit) {

        return commentService.getCommentsByPostSlug(slug, page, limit);
    }

    @GetMapping("/comments/{id}")
    public Comment getCommentById(@PathVariable Integer id) {
        return commentService.getCommentById(id);
    }

    @PostMapping("/posts/{id}/comments")
    public Comment createComment(@PathVariable Integer id, @RequestBody Comment comment) {
        return commentService.createComment(id, comment);
    }

}
