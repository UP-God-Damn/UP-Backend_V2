package com.dsm.up_backend_v2.domain.post.presentation;

import com.dsm.up_backend_v2.domain.post.domain.Post;
import com.dsm.up_backend_v2.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/post")
@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostService postService;
}
