package com.dsm.up_backend_v2.domain.comment.service;

import com.dsm.up_backend_v2.domain.comment.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

}
