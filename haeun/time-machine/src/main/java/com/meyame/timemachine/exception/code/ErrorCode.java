package com.meyame.timemachine.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode implements CInterface {

    DUPLICATE_EMAIL(-1,"중복되는 이메일입니다."),
    NOT_FOUND(-2,"사용자를 찾을 수 없습니다."),
    INCORRECT_PASSWORD(-3,"비밀번호가 일치하지 않습니다.");

    private final int code;
    private final String message;
}
