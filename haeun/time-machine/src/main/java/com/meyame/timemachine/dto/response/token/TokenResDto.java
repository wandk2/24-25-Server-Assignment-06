package com.meyame.timemachine.dto.response.token;

import lombok.Builder;

@Builder
public record TokenResDto(
        String accessToken
){
}
