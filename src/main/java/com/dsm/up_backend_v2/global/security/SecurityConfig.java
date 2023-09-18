package com.dsm.up_backend_v2.global.security;

import com.dsm.up_backend_v2.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor

public class SecurityConfig extends SecurityConfigurerAdapter {

    private final JwtProvider jwtProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .cors()

                .and()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/user/*").permitAll()
                .antMatchers("/post/search", "/post/postImage/*").permitAll()
                .antMatchers(HttpMethod.GET, "/user").authenticated()
                .anyRequest().authenticated()

                .and()
                .apply(new FilterConfig(jwtProvider))
                .and().build();

    }

}
