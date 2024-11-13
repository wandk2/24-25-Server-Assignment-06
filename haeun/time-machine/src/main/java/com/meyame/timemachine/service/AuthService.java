package com.meyame.timemachine.service;

import com.meyame.timemachine.dto.request.auth.SignInReqDto;
import com.meyame.timemachine.dto.response.token.TokenResDto;
import com.meyame.timemachine.exception.code.ErrorCode;
import com.meyame.timemachine.exception.CustomException;
import com.meyame.timemachine.domain.auth.User;
import com.meyame.timemachine.dto.request.auth.SignUpReqDto;
import com.meyame.timemachine.dto.response.auth.SignUpResDto;
import com.meyame.timemachine.jwt.JwtTokenProvider;
import com.meyame.timemachine.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    // 회원가입
    @Transactional
    public SignUpResDto signUp(SignUpReqDto signUpReqDto) {
        // 이메일 중복 체크
        String email = signUpReqDto.email();
        if (userRepository.existsByEmail(email)) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }
        // 패스워드 암호화
        String password = signUpReqDto.password();
        String encodedPassword = passwordEncoder.encode(password);

        User user = userRepository.save(User.builder()
                .email(signUpReqDto.email())
                .password(encodedPassword)
                .name(signUpReqDto.name())
                .build());
        return SignUpResDto.from(user);
    }

    // 로그인
    @Transactional
    public TokenResDto signIn(SignInReqDto signInReqDto) {
        User user = userRepository.findByEmail(signInReqDto.email())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
        // 비밀번호 검증
        if(!passwordEncoder.matches(signInReqDto.password(), user.getPassword())) {
            throw new CustomException(ErrorCode.INCORRECT_PASSWORD);
        }
        // JWT 토큰 생성
        String token = jwtTokenProvider.createAccessToken(user);
        return new TokenResDto(token);
    }
}
