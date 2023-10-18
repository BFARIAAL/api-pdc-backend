package com.jlr.ttl.ds.api;

import com.jlr.ttl.ds.api.dto.authentication.JwtUtils;


/**
 * @author PCORREI1
 *<p>
 * Main class to generate tokens in the backend - prints tokens to the console. This class should be used to generate a token.
 * The properties of the token can be adjusted in the JwtUtils.generateToken() function, where further authorities can be granted/removed.
 * Each token generated is unique and should only allow access to the endpoints for which authorities match.
 */
public class TokenGenerator {
    public static void main(String[] args){
        JwtUtils jwtUtils =  new JwtUtils();
        String token = jwtUtils.generateToken();
        System.out.println(token);

    }

}