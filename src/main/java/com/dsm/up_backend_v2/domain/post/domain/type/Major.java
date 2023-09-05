package com.dsm.up_backend_v2.domain.post.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Major {
    BACKEND("백엔드"), FRONTEND("프론트엔드"), EMBEDDED("임베디드"), FLUTTER("플러터"), IOS("ios"), AOS("안드로이드"), DEVOPS("devops");

    private final String major;
}
