package com.oldcatlabs.blog.response.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponse {

    private Integer id;
    private String title;
    private String body;
    private String slug;
    private boolean isPublished;
    private Long createdAt;
    private Long publishedAt;
    private Long updatedAt;
    private int commentCount;

}
