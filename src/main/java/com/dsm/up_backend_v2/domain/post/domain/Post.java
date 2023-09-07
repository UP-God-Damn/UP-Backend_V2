package com.dsm.up_backend_v2.domain.post.domain;

import com.dsm.up_backend_v2.domain.post.domain.type.Major;
import com.dsm.up_backend_v2.domain.post.domain.type.State;
import com.dsm.up_backend_v2.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post extends BaseEntity {

    @Column(nullable = false, length = 25)
    private String title;

    @Column(nullable = false, length = 10000)
    private String content;

    @Column(nullable = false)
    private String language;

    private String postImage;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Major major;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    @Builder
    public Post(String title, String content, String language, Major major, State state) {
        this.title = title;
        this.content = content;
        this.language = language;
        this.major = major;
        this.state = state;
    }

}
