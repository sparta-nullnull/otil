package com.spartanullnull.otil.domain.user.service;

import com.spartanullnull.otil.domain.user.entity.User;
import com.spartanullnull.otil.domain.user.entity.*;
import com.spartanullnull.otil.domain.user.repository.*;
import com.spartanullnull.otil.jwt.*;
import lombok.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public String login(String accountId, String password) {
        try {
            User user = userRepository.findByAccountIdAndPassword(accountId, password).orElseThrow(
                () -> new UsernameNotFoundException("회원정보 불일치")
            );
            if (passwordEncoder.matches(password, user.getPassword())) {
                UserRoleEnum role = user.getRole();
                return jwtUtil.createToken(accountId, role);
            } else {
                throw new RuntimeException("잘못된 유저 정보");
            }
        } catch (UsernameNotFoundException e) {
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        } catch (Exception e) {
            throw new RuntimeException("로그인에 실패했습니다.", e);
        }
    }
}
