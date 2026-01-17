package com.oldcatlabs.blog.service;

import com.oldcatlabs.blog.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    public List<Comment> getCommentsByPostSlug(String slug, Integer page, Integer limit) {
        List<Comment> comments = new ArrayList<>();

        return comments;
    }

    public Comment getCommentById(Integer id) {
        return new Comment();
    }

    public Comment createComment(Integer id, Comment comment) {
        return comment;
    }
}
