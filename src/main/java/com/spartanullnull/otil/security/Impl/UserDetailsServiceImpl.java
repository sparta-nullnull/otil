package com.spartanullnull.otil.security.Impl;


import com.spartanullnull.otil.domain.user.entity.*;
import com.spartanullnull.otil.domain.user.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        User user = userRepository.findByAccountId(accountId)
            .orElseThrow(() -> new UsernameNotFoundException("Not Found " + accountId));

        return new UserDetailsImpl(user);
    }
}