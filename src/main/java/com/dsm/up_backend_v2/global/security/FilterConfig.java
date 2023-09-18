package com.dsm.up_backend_v2.global.security;

import com.dsm.up_backend_v2.global.security.jwt.JwtProvider;
import com.dsm.up_backend_v2.global.security.jwt.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class FilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtProvider jwtProvider;

    @Override
    public void configure(HttpSecurity builder) {
        JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(jwtProvider);
        builder.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
