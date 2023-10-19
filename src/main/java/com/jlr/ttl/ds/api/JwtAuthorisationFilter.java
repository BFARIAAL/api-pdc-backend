package com.jlr.ttl.ds.api;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * Configuration class that runs whenever there is an API HTTP request.
 * This runs for every GET/POST/PUT/DELETE request.
 * The filter is applied to determine if the user has permission to access the
 * request and the response is tailored to reflect RBAC.
 */
@Configuration
public class JwtAuthorisationFilter extends OncePerRequestFilter {
    private final ArrayList<String> authoritiesStringList = new ArrayList<String>();
    private final String defaultpath="/v1/vehicles/";

    /**
     * This method is responsible for evaluating the JWT Token Authorities,
     * examining the GET request path and controlling the issued response based on user permissions.
     * It also evaluates the validity of the JWT Token.
     *
     * @param filterChain - FilterChain - Represents the filter the connection undergoes.
     * Each time there is a new request the filter intercepts the request and may
     * modify the response depending on user authorisation.
     * @param request - HTTPServletRequest - Represents the HTTP request Call to the API.
     * Includes the bearer Auth token the request must include
     * @param response - HTTPServletResponse - Represents the response given back from the server to the client.
     * **/
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = getJwtFromRequest(request);
        authoritiesStringList.clear();
        if (jwt == null) {
            response.setStatus(HttpStatus.I_AM_A_TEAPOT.value());
            response.sendError(HttpStatus.I_AM_A_TEAPOT.value(), "Not Authenticated");
            return;
        }else {
            try {
                Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).getBody();
                List<GrantedAuthority> authorities = extractPermissions(claims);
                Authentication auth = new UsernamePasswordAuthenticationToken(null, null,authorities);
                String path = request.getServletPath();
                String[] cases = {defaultpath+"all", defaultpath+"by_id/"};
                int i;
                for(i = 0; i < cases.length; i++)
                    if(path.contains(cases[i])) break;
                switch(i) {
                    case 0:
                        checkAuthorities("Vehicles", response, auth);
                        break;
                    case 1:
                        checkAuthorities("Admin", response, auth);
                        break;
                     default:
                        auth.setAuthenticated(false);
                        break;
                }
                if(!auth.isAuthenticated()){
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Permissions invalid");
                    return;
                }

                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (JwtException e) {
                throw new IllegalStateException("Invalid JWT Token - The Token is either manually modified or has expired. \n" + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * This method is responsible for comparing the specific call path to the list of authorities in the JWT token. Each request made is compared against the specific token authorities.
     *
     * @param valToCheck - String - Represents the necessary authority needed to access the endpoint being considered. This will be compared against the list of authorities in the token.
     * @param auth - Authentication - Represents the authentication status. The user is authenticated by default so this status needs to be changed manually for the connection to be refused.
     * @param response - HTTPServletResponse - Represents the response given back from the server to the client. This response can have several statuses (OK - JSON Response is returned, Unauthorised - User does not have Authority to view the response, or "I'm a Teapot" - No Auth Token Provided)
     * **/
    private void checkAuthorities(String valToCheck, HttpServletResponse response, Authentication auth){
        if (authoritiesStringList.contains(valToCheck)) {
            response.setStatus(HttpStatus.OK.value());
        } else {
            auth.setAuthenticated(false);
        }
    }

    /**
     * Retrieves the JWT token from the Request. The JWT token should always be passed as the "Authorization" parameter of the Header. This is extracted via this method
     *
     * @param request - represents the request by the client, which must contain a valid JWT Token in the header
     * @return This method returns either the Bearer token provided in the HTTPRequest or null in case there is no token given
     * **/
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    /**
     * Extracts the authorities from the claims within a token, putting them in a list for later use. JWT tokens have a list of claims contained within them, which have a list of authorities (or roles) the user can access to.
     *
     * @param claims - Map of claims within the JWT token, which contain several aspects/specificities of the JWT token
     * @return This method returns the list of authorities in the specific JWT token
     * **/
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
