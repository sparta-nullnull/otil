package com.spartanullnull.otil.domain.comment.controller;

import com.spartanullnull.otil.domain.comment.dto.*;
import com.spartanullnull.otil.domain.comment.service.*;
import java.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;
    // 댓글 작성
    // postId       TIL ID
    // userId       사용자 ID
    // requestDto   댓글 작성 요청 정보
    // return       ResponseEntity<CommentResponseDto>
    //              작성된 댓글과 HTTP 상태 코드 반환
    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(
        @PathVariable Long postId,
        @RequestParam Long userId,
        @RequestBody CommentRequestDto requestDto
    ) {
        CommentResponseDto createdComment = commentService.createComment(postId, userId,
            requestDto);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    // 특정 게시물의 댓글 목록 조회
    // postId       TIL ID
    // return       ResponseEntity<List<CommentResponseDto>>
    //              댓글 목록과 HTTP 상태 코드 반환
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable Long postId) {
        List<CommentResponseDto> comments = commentService.getCommentsByPostId(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    // 댓글 수정
    // postId       TIL ID
    // commentId    댓글 ID
    // requestDto   수정할 댓글 정보
    // return       ResponseEntity<CommentResponseDto>
    //              수정된 댓글과 HTTP 상태 코드 반환
    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(
        @PathVariable Long postId,
        @PathVariable Long commentId,
        @RequestBody CommentRequestDto requestDto
    ) {
        CommentResponseDto updatedComment = commentService.updateComment(commentId, requestDto);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    // 댓글 삭제
    // postId       TIL ID
    // commentId    댓글 ID
    // return       ResponseEntity<Void> HTTP 상태 코드 반환
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
        @PathVariable Long postId,
        @PathVariable Long commentId
    ) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
