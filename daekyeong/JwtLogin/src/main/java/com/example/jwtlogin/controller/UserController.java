package com.example.jwtlogin.controller;

import com.example.jwtlogin.dto.TokenDto;
import com.example.jwtlogin.dto.UserInfoDto;
import com.example.jwtlogin.dto.UserSignDto;
import com.example.jwtlogin.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.http11.Http11InputBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<TokenDto> signUp(@RequestBody UserSignDto userSignDto) {
        TokenDto response = userService.signUp(userSignDto);

        return ResponseEntity.ok(response);
    }
    @GetMapping("/getUser")
    public ResponseEntity<UserInfoDto> getUser(Principal principal) {
        UserInfoDto userInfoDto = userService.findByPrincipal(principal);

        return ResponseEntity.ok(userInfoDto);
    }

    @DeleteMapping("/delUser")
    public ResponseEntity<UserInfoDto> delUser(Principal principal) {
        userService.deleteByPrincipal(principal);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
