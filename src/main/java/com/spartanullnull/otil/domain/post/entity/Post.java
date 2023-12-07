package com.spartanullnull.otil.domain.post.entity;

import com.fasterxml.jackson.annotation.*;
import com.spartanullnull.otil.domain.category.entity.*;
import com.spartanullnull.otil.domain.comment.entity.*;
import com.spartanullnull.otil.domain.like.entity.Like;
import com.spartanullnull.otil.domain.post.dto.*;
import com.spartanullnull.otil.domain.recommend.entity.*;
import com.spartanullnull.otil.domain.user.entity.*;
import com.spartanullnull.otil.util.*;
import jakarta.persistence.*;
import java.util.*;
import lombok.*;

@Getter
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTime {

    @JsonIgnore
    @OneToMany(targetEntity = Comment.class, mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Comment> comments = new ArrayList<>();
    @JsonIgnore
    @OneToMany(targetEntity = Category.class, mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Category> categories = new ArrayList<>();
    @JsonIgnore
    @OneToMany(targetEntity = Recommend.class, mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Recommend> recommends = new ArrayList<>();
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Like> likes = new ArrayList<>();

    public Post(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}