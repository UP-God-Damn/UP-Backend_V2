package com.dsm.up_backend_v2.domain.post.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum State {
    QUESTION("질문"), SOLUTION("해결");

    private final String state;
}
