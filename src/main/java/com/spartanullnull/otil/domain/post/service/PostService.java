package com.spartanullnull.otil.domain.post.service;

import com.spartanullnull.otil.domain.post.dto.*;
import com.spartanullnull.otil.domain.post.entity.*;
import com.spartanullnull.otil.domain.post.repository.*;
import lombok.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post = Post.builder()
            .title(requestDto.getTitle())
            .content(requestDto.getContent())
            .build();
        postRepository.save(post);

        return PostResponseDto.of(post);
    }

    public PostResponseDto getPost(Long postId) {
        Post post = findById(postId);
        return PostResponseDto.of(post);
    }

    public Post findById(Long postId) {
        return postRepository.findById(postId)
            .orElseThrow(() -> new NullPointerException("해당 TIL이 존재하지 않아요!"));
    }
}
