package com.oldcatlabs.blog.request.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCommentRequest {

    @NotBlank(message = "name is required")
    @Size(min = 1, max = 100, message = "name must between 1 to 100 characters")
    private String name;

    @NotBlank(message = "email is required")
    @Size(min = 1, max = 100, message = "email must between 1 to 100 characters")
    private String email;

    @NotBlank(message = "body is required")
    private String body;

}
