package com.jlr.ttl.ds.api.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author PCORREI1
 *<p>
 * Configuration class necessary to create a JWT Token Bean. This is needed to run the JWT Token Dependencies.
 */
@Configuration
public class JwtConfig {

    @Bean
    public JwtUtils jwtUtils() {
        return new JwtUtils();
    }

}