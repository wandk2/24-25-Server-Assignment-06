package com.meyame.timemachine.controller;

import com.meyame.timemachine.dto.request.auth.SignInReqDto;
import com.meyame.timemachine.dto.request.auth.SignUpReqDto;
import com.meyame.timemachine.dto.response.token.TokenResDto;
import com.meyame.timemachine.dto.response.auth.SignUpResDto;
import com.meyame.timemachine.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * @param signUpReqDto 회원가입 요청 DTO
     * @return 성공 시 회원가입 응답 DTO(id, email, name)와 200 OK 반환
     */
    @PostMapping("/signUp")
    public ResponseEntity<SignUpResDto> signUp(@RequestBody SignUpReqDto signUpReqDto) {
        SignUpResDto res = authService.signUp(signUpReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    /**
     * @param signInReqDto 로그인 요청 DTO
     * @return 성공 시 JWT 토큰과 200 OK 반환
     */
    @PostMapping("/signIn")
    public ResponseEntity<TokenResDto> signIn(@RequestBody SignInReqDto signInReqDto) {
        TokenResDto token = authService.signIn(signInReqDto);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
