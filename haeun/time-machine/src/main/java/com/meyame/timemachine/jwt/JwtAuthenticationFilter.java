package com.meyame.timemachine.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

/*
JWT 토큰을 기반으로 사용자의 인증 상태를 확인하고
SecurityContext 에 인증 객체를 설정하는 필터
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        // 요청에서 토큰을 추출
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        // token 이 비어있지 않거나, 유효한 토큰이면
        if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {

            // 토큰에서 사용자를 가져오고, getAuthentication() 함수에서
            // 사용자의 권한 정보를 securityContextHolder에 담아준다.
            Authentication authentication = jwtTokenProvider.getAuthentication(token);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
