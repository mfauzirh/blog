package com.oldcatlabs.blog.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class CreatePostRequest {

    @NotBlank(message = "title is required")
    @Size(min = 1, max = 100, message = "title must be 1 to 100 characters long")
    private String title;

    @NotBlank(message = "title is required")
    @Size(min = 10, message = "body must atleast contains 1 character")
    private String body;

    @NotBlank(message = "title is required")
    @Size(min = 1, max = 100, message = "slug must be 1 to 100 characters long")
    private String slug;

}
