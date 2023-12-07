package com.spartanullnull.otil.global.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonResponseDto {

    private String msg;
    private Integer statusCode;

}
