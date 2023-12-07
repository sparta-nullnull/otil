package com.spartanullnull.otil.domain.category.entity;

import com.fasterxml.jackson.annotation.*;
import com.spartanullnull.otil.domain.post.entity.*;
import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
}
