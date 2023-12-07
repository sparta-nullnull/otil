package com.spartanullnull.otil.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class SignupRequestDto {

    @Pattern(regexp = "^[a-z0-9]{4,10}$")
    private String accountId;

    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\",./<>?\\\\|]{8,15}$")
    private String password;

    private String nickname;

    @Email
    private String email;

    private boolean admin = false;

    private String adminToken = "";

}
