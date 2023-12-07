package com.spartanullnull.otil.domain.like.repository;

import com.spartanullnull.otil.domain.like.entity.Like;
import com.spartanullnull.otil.domain.post.entity.Post;
import com.spartanullnull.otil.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {

    boolean existsByUserAndPost(User user, Post post);

    void deleteByUserAndPost(User user, Post post);

    Long countByPostId(Long postId);
}
