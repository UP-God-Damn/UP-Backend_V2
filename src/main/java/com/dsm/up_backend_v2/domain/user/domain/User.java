package com.dsm.up_backend_v2.domain.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @Id
    @Column(nullable = false, length = 12)
    private String accountId;

    @Column(nullable = false, length = 12)
    private String nickname;

    @Column(nullable = false, length = 60)
    private String password;

    private String profileImage;

    @Builder
    public User(String accountId, String nickname, String password) {
        this.accountId = accountId;
        this.nickname = nickname;
        this.password = password;
    }
}
