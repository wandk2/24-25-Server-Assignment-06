package com.meyame.timemachine.dto.request.user;

import com.meyame.timemachine.domain.auth.Role;
import com.meyame.timemachine.domain.auth.User;

public record UserUpdateReqDto(
        String email,
        String password,
        String name
){}
