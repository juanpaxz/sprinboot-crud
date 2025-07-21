package com.juan.curso.springboot.app.sprinbootcrud.security;


import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;

public class TokenConfig {
    public static final SecretKey JWT_SECRET_KEY = Jwts.SIG.HS256.key().build();
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "application/json";
}
