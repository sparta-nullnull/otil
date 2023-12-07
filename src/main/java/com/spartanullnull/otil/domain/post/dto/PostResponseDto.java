package com.spartanullnull.otil.domain.post.dto;

import com.spartanullnull.otil.domain.post.entity.*;
import com.spartanullnull.otil.global.dto.*;
import java.time.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostResponseDto extends CommonResponseDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    //    private String username;
    public static PostResponseDto of(Post post) {
        return PostResponseDto.builder()
            .id(post.getId())
            .title(post.getTitle())
            .content(post.getContent())
            .createdAt(post.getCreatedAt())
            .build();
    }

}
