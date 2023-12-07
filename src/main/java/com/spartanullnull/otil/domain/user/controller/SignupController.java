package com.spartanullnull.otil.domain.user.controller;

import com.spartanullnull.otil.domain.user.dto.*;
import com.spartanullnull.otil.domain.user.service.*;
import com.spartanullnull.otil.global.dto.*;
import jakarta.validation.*;
import java.util.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class SignupController {

    private final SignupService signupService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDto> signup(@Valid @RequestBody SignupRequestDto requestDto,
        BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return ResponseEntity.badRequest()
                .body(new CommonResponseDto("회원가입 실패", HttpStatus.BAD_REQUEST.value()));
        }
        signupService.singup(requestDto);
        return ResponseEntity.ok()
            .body(new CommonResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
    }
}
