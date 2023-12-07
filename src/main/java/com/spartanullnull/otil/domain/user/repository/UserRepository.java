package com.spartanullnull.otil.domain.user.repository;

import com.spartanullnull.otil.domain.user.entity.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByAccountId(String accountId);

    Optional<User> findByEmail(String email);

    Optional<User> findByAccountIdAndPassword(String accountId, String password);

    Optional<User> findByNickname(String nickname);

    Optional<User> findByKakaoId(Long kakaoId);
}
