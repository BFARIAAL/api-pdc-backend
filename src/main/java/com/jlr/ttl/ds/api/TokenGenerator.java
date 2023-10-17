package com.jlr.ttl.ds.api;

import com.jlr.ttl.ds.api.dto.authentication.JwtUtils;


public class TokenGenerator {
    public static void main(String[] args){
        JwtUtils jwtUtils =  new JwtUtils();
        String token = jwtUtils.generateToken();

        System.out.println(token);

    }

}