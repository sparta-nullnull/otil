package com.spartanullnull.otil.domain.like.entity;

import com.fasterxml.jackson.annotation.*;
import com.spartanullnull.otil.domain.comment.entity.*;
import com.spartanullnull.otil.domain.post.entity.Post;
import com.spartanullnull.otil.domain.user.entity.User;
import com.spartanullnull.otil.util.BaseTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "likes")
public class Like extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Comment comment;
}
