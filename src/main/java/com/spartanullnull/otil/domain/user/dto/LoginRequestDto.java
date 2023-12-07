package com.spartanullnull.otil.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class LoginRequestDto {

    public String accountId;
    public String password;

}
