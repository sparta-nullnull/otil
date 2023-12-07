package com.spartanullnull.otil.domain.user.service;

import com.spartanullnull.otil.domain.user.dto.*;
import com.spartanullnull.otil.domain.user.entity.*;
import com.spartanullnull.otil.domain.user.repository.*;
import java.util.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ADMIN_TOKEN
    @Value("${admin.token}")
    private String ADMIN_TOKEN;

    public void singup(SignupRequestDto requestDto) {
        String accountId = requestDto.getAccountId();
        String rawPassword = requestDto.getPassword();
        String password = passwordEncoder.encode(rawPassword);

        Optional<User> checkAccountId = userRepository.findByAccountId(accountId);
        if (checkAccountId.isPresent()) {
            throw new IllegalArgumentException("중복된 아이디 존재");
        }

        String nickname = requestDto.getNickname();
        Optional<User> checkNickName = userRepository.findByNickname(nickname);
        if (checkNickName.isPresent()) {
            throw new IllegalArgumentException("중복된 닉네임 존재");
        }

        String email = requestDto.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 이메일 존재");
        }

        UserRoleEnum roleEnum = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 상이합니다.");
            }
            roleEnum = UserRoleEnum.ADMIN;
        }

        User user = new User(accountId, password, nickname, email, roleEnum);
        userRepository.save(user);
    }
}
