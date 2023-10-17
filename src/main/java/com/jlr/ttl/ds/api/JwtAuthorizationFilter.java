package com.jlr.ttl.ds.api;

import com.jlr.ttl.ds.api.constants.DSConstants;
import com.jlr.ttl.ds.api.dto.DSResponse;
import com.jlr.ttl.ds.api.dto.authentication.JwtUtils;
import com.jlr.ttl.ds.api.dto.response.VehicleResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Configuration
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    JwtUtils token;
    List<String> authoritiesStringList = new ArrayList<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = getJwtFromRequest(request);
        authoritiesStringList.clear();
        if (jwt != null) {
            try {
                Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).getBody();
                List<GrantedAuthority> authorities = extractPermissions(claims);
                Authentication auth = new UsernamePasswordAuthenticationToken(null, null,authorities);

                if(request.getServletPath().startsWith("/v1/vehicles/all")){
                    if(authoritiesStringList.contains("Vehicles")){
                        response.setStatus(HttpStatus.OK.value());
                    }else{
                        auth.setAuthenticated(false);
                    }
                }else if(request.getServletPath().startsWith("/v1/vehicles/by_id/")) {
                    if (authoritiesStringList.contains("Admin")) {
                        response.setStatus(HttpStatus.OK.value());
                    } else {
                        auth.setAuthenticated(false);
                    }
                }else{
                    auth.setAuthenticated(false);
                }

                if(!auth.isAuthenticated()){
                        response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Permissions invalid");
                    return;
                }

                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (JwtException e) {
                throw new IllegalStateException("Invalid JWT");
            }
        }


        filterChain.doFilter(request, response);
    }

    public String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public List<GrantedAuthority> extractPermissions(Claims claims) {

        List<LinkedHashMap<String,String>> permissions = claims.get("authorities", List.class);

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (permissions != null) {
            permissions.forEach(permission -> {
                authorities.add(new SimpleGrantedAuthority(String.join("", permission.values())));
                authoritiesStringList.add(String.join("", permission.values()));
            });
        }

        return authorities;

    }
}
