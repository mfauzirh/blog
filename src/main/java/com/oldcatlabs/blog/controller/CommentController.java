package com.oldcatlabs.blog.controller;

import com.oldcatlabs.blog.entity.Comment;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CommentController {

    @GetMapping("/posts/{slug}/comments")
    public List<Comment> getComments(
            @PathVariable String postSlug, @RequestParam Integer page, @RequestParam Integer limit) {
        List<Comment> comments = new ArrayList<>();

        return comments;
    }

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable Integer id) {
        return new Comment();
    }

    @PostMapping("/posts/{id}/comments")
    public Comment createComment(@PathVariable Integer id, @RequestBody Comment comment) {
        return comment;
    }

}
