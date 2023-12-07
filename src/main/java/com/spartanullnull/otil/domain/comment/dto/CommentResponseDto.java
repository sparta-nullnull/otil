package com.spartanullnull.otil.domain.comment.dto;

import com.spartanullnull.otil.domain.comment.entity.*;
import lombok.*;

@Getter
@Builder
public class CommentResponseDto { // 클라이언트에게 응답할 때 반환하는 데이터를 정의

    private final Long id;
    private final String commentText;   // 댓글 텍스트
    private final Long userId;          // 사용자 ID(PK)
    private final String accountId;      // 사용자 계정 ID
    private final Long postId;          // TIL ID


    // Comment Entity에서 CommentResponseDto로 변환하는 메서드
    public static CommentResponseDto fromEntity(Comment comment) {
        return CommentResponseDto.builder()
            .id(comment.getId())
            .commentText(comment.getCommentText())
            .userId(comment.getId())
            .accountId(comment.getUser().getAccountId())
            .postId(comment.getPost().getId())
            .build();
    }
}
