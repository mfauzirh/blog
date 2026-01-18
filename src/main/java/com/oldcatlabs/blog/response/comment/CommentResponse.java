package com.oldcatlabs.blog.response.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {

    private Integer id;
    private String name;
    private String email;
    private String body;
    private Long createdAt;

    private Post post;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {
        private String id;
        private String title;
        private String slug;
    }

}
