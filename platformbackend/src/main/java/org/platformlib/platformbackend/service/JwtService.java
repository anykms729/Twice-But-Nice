package org.platformlib.platformbackend.service;

import io.jsonwebtoken.Claims;

import java.util.Map;

public interface JwtService {
    //    String getToken(String key, Object value);
    String getToken(Map<String, Object> claims);

    Claims getClaims(String token);

    boolean isValid(String token);

    int getId(String token);

    String getEmail(String token);
}
