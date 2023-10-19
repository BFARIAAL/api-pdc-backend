package com.jlr.ttl.ds.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * Class responsible for configuring which paths are redirected
 * to go through the filter for access control - Security Configuration for Spring Security
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * This method specifies which endpoints are subject to go through the filter.
     * This is set by default for all paths.
     * The application context path is /ds this method requires that all /ds/v1 calls should be subject to the security filter.
     * This can be adjusted to support further releases
     * @return Returns a modification for the configuration of the application HTTP calls, where a filter will now be applied.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/v1");
        return http.build();
    }


}

