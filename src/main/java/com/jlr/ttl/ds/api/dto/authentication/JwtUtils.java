package com.jlr.ttl.ds.api.dto.authentication;

import org.springframework.core.convert.converter.Converter;
import io.jsonwebtoken.*;
import org.hibernate.internal.util.collections.ArrayHelper;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JwtUtils {

    private String secret = "secret";

    public static class AuthorityAuthenticationProvider implements AuthenticationProvider {

        @Override
        public Authentication authenticate(Authentication authentication) {
            return new UsernamePasswordAuthenticationToken(null, null,
                    authentication.getAuthorities());
        }

        @Override
        public boolean supports(Class<?> authentication) {
            return false;
        }

    }

    public String generateToken() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", List.of(new SimpleGrantedAuthority("Vehicles")));
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

//    private Boolean isTokenExpired(String token) {
//        final Date expiration = getExpirationDateFromToken(token);
//        return expiration.before(new Date());
//    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

}
