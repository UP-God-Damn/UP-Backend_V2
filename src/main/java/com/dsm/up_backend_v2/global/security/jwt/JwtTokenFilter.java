package com.dsm.up_backend_v2.global.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.context.SecurityContextHolder;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        String token = resolveToken(request);

        if(token!= null && jwtProvider.validate(token)){
            Authentication authentication = jwtProvider.authentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);

    }

    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader(HEADER);
        if (bearer != null && bearer.startsWith(PREFIX)) return bearer.replace((PREFIX), "");
        return null;
    }
}
