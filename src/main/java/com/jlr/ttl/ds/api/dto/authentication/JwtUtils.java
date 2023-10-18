package com.jlr.ttl.ds.api.dto.authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author PCORREI1
 *<p>
 * This class allows generating JWT tokens with a specific secret. The tokens are encrypted with a specific SignatureAlgorithm to avoid manual tempering of the token by the user.
 */
public class JwtUtils {
    private final String secret = "secret";

    /**
     * This method is responsible for generating a token with a specific List of permissions.
     * The claims necessary should be specified in the ListOf argument of the "authorities" claim as new SimpleGrantedAuthority("role").
     * @return returns the generated token with a list of authorities (endpoints the user can access - to be compared in the Filter) and an expiry date/generation date
     * **/
    public String generateToken() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", List.of(new SimpleGrantedAuthority("Vehicles"), new SimpleGrantedAuthority("Locations")));
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
