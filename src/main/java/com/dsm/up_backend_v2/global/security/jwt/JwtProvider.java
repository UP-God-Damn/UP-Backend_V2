package com.dsm.up_backend_v2.global.security.jwt;

import com.dsm.up_backend_v2.global.security.auth.AuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private static final String HEADER = "Authorization";

    private final AuthDetailsService authDetailsService;

    @Value("${spring.jwt.secretKey}")
    private String secretKey;

    @Value("$${spring.jwt.access}")
    private Long access;

    @Value("${spring.jwt.refresh}")
    private Long refresh;


    private String generateToken(String accountId) {
        return Jwts.builder()
                .setHeaderParam("typ", "access")
                .signWith(SignatureAlgorithm.ES256, secretKey)
                .setSubject(accountId)
                .setExpiration(new Date(System.currentTimeMillis() + access * 1000))
                .compact();
    }

    private String generateRefreshToken(String accountId) {
        return Jwts.builder()
                .setHeaderParam("typ", "refresh")
                .signWith(SignatureAlgorithm.ES256, secretKey)
                .setSubject(accountId)
                .setExpiration(new Date(System.currentTimeMillis() + refresh * 1000))
                .compact();
    }

    public Authentication authentication(String token) { //토큰을 이용하여 사용자의 정보를 가져옴
        UserDetails userDetails = authDetailsService.loadUserByUsername(getSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());    }

    public boolean validate(String token){ //토큰의 유효성 검사
        isRefreshToken(token);

        try{
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch(ExpiredJwtException e) { //만료된 토큰
            throw new RuntimeException();
        } catch(IllegalArgumentException e) { //잘못된 토큰
            throw new RuntimeException();
        } catch(UnsupportedJwtException e) { //지원되지 않는 토큰
            throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private void isRefreshToken(String token) { //리프레시토큰인지 확인
        if(getHeader(token).equals("refresh")) throw new RuntimeException();
    }

    private Header getHeader(String token) { //주어진 토큰의헤더를 추출
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getHeader();
    }

    public String getSubject(String token) { //토큰의
        try {
            return getBody(token).getSubject();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private Claims getBody(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader(HEADER);
        return(bearer);
    }


}
