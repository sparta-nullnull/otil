package com.spartanullnull.otil.domain.like.service;

import com.spartanullnull.otil.domain.like.entity.Like;
import com.spartanullnull.otil.domain.like.repository.LikeRepository;
import com.spartanullnull.otil.domain.post.entity.Post;
import com.spartanullnull.otil.domain.post.repository.PostRepository;
import com.spartanullnull.otil.domain.user.entity.User;
import com.spartanullnull.otil.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    /**
     * 사용자가 TIL에 좋아요를 누르는 메서드
     *
     * @param userId    좋아요를 누르는 사용자
     * @param postId    좋아요를 누를 게시물 ID
     */
    @Transactional
    public void createLikeForPost(Long userId , Long postId) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new EntityNotFoundException("TIL을 찾을 수 없습니다."));

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다."));

        if (!likeRepository.existsByUserAndPost(user, post)) {
            likeRepository.save(Like.builder().user(user).post(post).build());
        }
    }

    /**
     * 사용자가 TIL에 좋아요를 취소하는 메서드
     *
     * @param userId    좋아요 취소하는 사용자
     * @param postId    좋아요를 취소할 게시물 ID
     */
    @Transactional
    public void deleteLikeForPost(Long userId, Long postId) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new EntityNotFoundException("TIL을 찾을 수 없습니다."));

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("TIL을 찾을 수 없습니다."));

        if (likeRepository.existsByUserAndPost(user, post)) {
            likeRepository.deleteByUserAndPost(user, post);
        }
    }

    /**
     *
     * @param postId    조회할 게시물 ID
     * @return          게시물의 좋아요 개수
     */
    public Long getLikeCountForPost(Long postId) {
        return likeRepository.countByPostId(postId);
    }
}