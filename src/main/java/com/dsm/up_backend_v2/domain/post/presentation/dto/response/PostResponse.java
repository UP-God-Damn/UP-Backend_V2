package com.dsm.up_backend_v2.domain.post.presentation.dto.response;

import com.dsm.up_backend_v2.domain.comment.presentation.dto.response.CommentResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PostResponse {
    private final Long id;
    private final String accountId;
    private final String userNickname;
    private final String title;
    private final String content;
    private final String language;
    private final String state;
    private final String major;
    private final String createDate;
    private final List<CommentResponse> comments;

    @Builder
    private PostResponse(Long id, String accountId, String userNickname, String title, String content, String language, String state, String major, String createDate, List<CommentResponse> comments){
        this.id = id;
        this.accountId = accountId;
        this.userNickname = userNickname;
        this.title = title;
        this.content = content;
        this.language = language;
        this.state = state;
        this.major = major;
        this.createDate = createDate;
        this.comments = comments;
    }
}
