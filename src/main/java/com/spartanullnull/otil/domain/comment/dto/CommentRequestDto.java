package com.spartanullnull.otil.domain.comment.dto;

import lombok.*;

@Getter
@Builder
public class CommentRequestDto { // 클라이언트로부터 댓글을 생성할 때 받는 데이터를 정의함

    private final String commentText;
}