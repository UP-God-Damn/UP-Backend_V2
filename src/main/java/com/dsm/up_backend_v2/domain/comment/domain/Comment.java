package com.dsm.up_backend_v2.domain.comment.domain;

import com.dsm.up_backend_v2.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment extends BaseEntity {

    @Column(nullable = false, length = 5000)
    private String content;

    public Comment(String content) {
        this.content = content;
    }
}
