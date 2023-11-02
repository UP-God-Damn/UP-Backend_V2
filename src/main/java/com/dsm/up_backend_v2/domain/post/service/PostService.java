package com.dsm.up_backend_v2.domain.post.service;

import com.dsm.up_backend_v2.domain.post.domain.Post;
import com.dsm.up_backend_v2.domain.post.domain.repository.PostRepository;
import com.dsm.up_backend_v2.domain.post.domain.type.Major;
import com.dsm.up_backend_v2.domain.post.domain.type.State;
import com.dsm.up_backend_v2.domain.post.exception.PostNotFoundException;
import com.dsm.up_backend_v2.domain.post.exception.UserNotMatchException;
import com.dsm.up_backend_v2.domain.post.presentation.dto.request.PostRequest;
import com.dsm.up_backend_v2.domain.post.presentation.dto.response.PostListResponse;
import com.dsm.up_backend_v2.global.aws.S3Util;
import com.dsm.up_backend_v2.global.aws.exception.ImageUploadFailException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final S3Util s3Util;

    @Transactional
    public Long create(PostRequest request) {
        Post post = postRepository.save(Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .language(request.getLanguage())
                .state(State.valueOf(request.getState()))
                .major(Major.valueOf(request.getMajor()))
                .build());

        return post.getId();
    }

    public Long update(Long id, PostRequest request, MultipartFile file, String userId) {
        Post post = postRepository.findById(id).orElseThrow(() -> PostNotFoundException.EXCEPTION);

        if(!post.getUser().getAccountId().equals(userId)) throw UserNotMatchException.EXCEPTION;

        if (file != null) {
            if (post.getPath() != null) s3Util.delete(post.getPath());
            post.updatePath(s3Util.upload(file));
        }
        return post.update(request.getTitle(), request.getContent(), request.getLanguage(), State.valueOf(request.getState()), Major.valueOf(request.getMajor()));
    }

    public void delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> PostNotFoundException.EXCEPTION);

        String currentPath = post.getPath();
        if (currentPath != null && !currentPath.isEmpty()) {
            s3Util.delete(currentPath);
        }

        postRepository.delete(post);
    }

    public void postImage(Long id, MultipartFile file, String userId) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
        if(!post.getUser().getAccountId().equals(userId)) throw UserNotMatchException.EXCEPTION;

        String currentPath = post.getPath();
        if (currentPath != null && !currentPath.isEmpty()) {
            s3Util.delete(currentPath);
        }

        post.updatePath(s3Util.upload(file));
    }

}