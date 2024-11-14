package com.example.jwtlogin.service;

import com.example.jwtlogin.UserRepository;
import com.example.jwtlogin.domain.User;
import com.example.jwtlogin.dto.TokenDto;
import com.example.jwtlogin.dto.UserInfoDto;
import com.example.jwtlogin.dto.UserSignDto;
import com.example.jwtlogin.jwt.TokenProvider;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Transactional
    public TokenDto signUp(UserSignDto userSignDto) {
        User user = userRepository.save(User.builder()
                .userId(userSignDto.getUserId())
                .userName(userSignDto.getUsername())
                .password(passwordEncoder.encode(userSignDto.getPassword()))
                .build());

        String accessToken = tokenProvider.createAccessToken(user);

        return TokenDto.builder()
                .token(accessToken)
                .build();
    }

    @Transactional(readOnly = true)
    public UserInfoDto findByPrincipal(Principal principal) {
        Long userId = Long.parseLong(principal.getName());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다"));

        return UserInfoDto.builder()
                .userId(user.getUserId())
                .username(user.getUserName())
                .role(user.getRole().name())
                .build();
    }

    @Transactional
    public void deleteByPrincipal(Principal principal) {
        Long userId = Long.parseLong(principal.getName());

        userRepository.deleteById(userId);
    }
}
