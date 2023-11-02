package com.dsm.up_backend_v2.domain.post.presentation;

import com.dsm.up_backend_v2.domain.post.domain.Post;
import com.dsm.up_backend_v2.domain.post.presentation.dto.request.PostRequest;
import com.dsm.up_backend_v2.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RequestMapping("/post")
@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody PostRequest request) {
        return postService.create(request);
    }

    @PatchMapping(value = "/{id}")
    public Long update(@PathVariable @NotNull Long id, @RequestBody @Valid PostRequest request, @RequestParam(required = false) MultipartFile file, @RequestHeader("userId") String userId) {
        return postService.update(id, request, file, userId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        postService.delete(id);
    }

    @PostMapping("/{id}/image")
    public void postImage(@PathVariable Long id, @RequestParam MultipartFile file, @RequestHeader("userId") String userId) {
        postService.postImage(id, file, userId);
    }
}
