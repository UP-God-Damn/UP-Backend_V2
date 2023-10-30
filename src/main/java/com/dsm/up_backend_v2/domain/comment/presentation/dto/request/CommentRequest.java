package com.dsm.up_backend_v2.domain.comment.presentation.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CommentRequest {
    @Getter
    @NotNull
    private Long id;

    @NotBlank
    @Size(min = 5, max = 5000)
    private String content;
}
