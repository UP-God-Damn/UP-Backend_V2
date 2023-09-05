package com.dsm.up_backend_v2.domain.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String accountId;

    @Column(nullable = false, length = 12)
    private String nickName;

    @Column(nullable = false, length = 50)
    private String password;

    private String profileImage;

    @Builder
    public User(String accountId, String nickName, String password) {
        this.accountId = accountId;
        this.nickName = nickName;
        this.password = password;
    }
}
