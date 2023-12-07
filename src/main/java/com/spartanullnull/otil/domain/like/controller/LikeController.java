package com.spartanullnull.otil.domain.like.controller;

import com.spartanullnull.otil.domain.like.service.LikeService;
import com.spartanullnull.otil.security.Impl.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    // 좋아요 누르기 API
    @PostMapping("/{postId}/like")
    public ResponseEntity<String> createLikeForPost(
        @PathVariable Long postId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        likeService.createLikeForPost(userDetails.getUser().getId(), postId);
        return ResponseEntity.ok("좋아요 성공!");
    }

    // 좋아요 취소하기 API
    @DeleteMapping("/{postId}/like")
    public ResponseEntity<String> deleteLikeForPost(
        @PathVariable Long postId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        likeService.deleteLikeForPost(userDetails.getUser().getId(), postId);
        return ResponseEntity.ok("좋아요 취소!");
    }

    // 좋아요 개수 조회 API
    @GetMapping("/{postId}/count")
    public ResponseEntity<Long> getLikeCountForPost(@PathVariable Long postId) {
        Long likeCount = likeService.getLikeCountForPost(postId);
        return ResponseEntity.ok(likeCount);
    }
}