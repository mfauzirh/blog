package com.oldcatlabs.blog.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponse {

    private Integer id;
    private String name;
    private String email;
    private String body;
    private Long createdAt;
    private Integer postId;

}
