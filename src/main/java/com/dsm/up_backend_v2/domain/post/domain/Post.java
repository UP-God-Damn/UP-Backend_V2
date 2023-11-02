package com.dsm.up_backend_v2.domain.post.domain;

import com.dsm.up_backend_v2.domain.comment.domain.Comment;
import com.dsm.up_backend_v2.domain.post.domain.type.Major;
import com.dsm.up_backend_v2.domain.post.domain.type.State;
import com.dsm.up_backend_v2.domain.user.domain.User;
import com.dsm.up_backend_v2.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 25)
    private String title;

    @Column(nullable = false, length = 10000)
    private String content;

    @Column(nullable = false)
    private String language;

    private String postImage;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Major major;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    private String path;

    private String createdDate;

    @Builder
    public Post(User user, String title, String content, String language, Major major, State state, String createdDate) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.language = language;
        this.major = major;
        this.state = state;
        this.createdDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy / MM / dd"));
    }

    public Long update(String title, String content, String language, State state, Major major) {
        this.title = title;
        this.content = content;
        this.language = language;
        this.state = state;
        this.major = major;
        return this.id;
    }

    public String updatePath(String path) {
        this.path = path;
        return this.path;
    }
}
