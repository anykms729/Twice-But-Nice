package org.platformlib.platformbackend.service;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("jwtService")
public class JwtServiceImpl implements JwtService {

    private String secretKey = "abbci2ioadij@@@ai17a662###8139!!!18ausudahd178316738687687@@ad6g";

//    @Override
//    public String getToken(String key, Object value) {
//
//        Date expTime = new Date();
//        expTime.setTime(expTime.getTime() + 1000 * 60 * 30);
//        byte[] secretByteKey = Base64.getEncoder().encodeToString(secretKey.getBytes()).getBytes();
//        Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS256.getJcaName());
//
//        Map<String, Object> headerMap = new HashMap<>();
//        headerMap.put("typ", "JWT");
//        headerMap.put("alg", "HS256");
//
//        Map<String, Object> map = new HashMap<>();
//        map.put(key, value);
//
//        JwtBuilder builder = Jwts.builder().setHeader(headerMap)
//                .setClaims(map)
//                .setExpiration(expTime)
//                .signWith(signKey, SignatureAlgorithm.HS256);
//
//        return builder.compact();
//    }


    @Override
    public String getToken(Map<String, Object> claims) {
        long currentTimeMillis = System.currentTimeMillis();
        Date expTime = new Date();
        expTime.setTime(expTime.getTime() + 1000 * 60 * 30);

        byte[] secretByteKey = Base64.getEncoder().encodeToString(secretKey.getBytes()).getBytes();
        Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS256.getJcaName());

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setExpiration(expTime)
                .signWith(signKey, SignatureAlgorithm.HS256);

        return builder.compact();
    }

    @Override
    public Claims getClaims(String token) {
        if (token != null && !"".equals(token)) {
            try {
                byte[] secretByteKey = Base64.getEncoder().encodeToString(secretKey.getBytes()).getBytes();
                Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS256.getJcaName());
                Claims claims = Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token).getBody();
                return claims;
            } catch (ExpiredJwtException e) {
                // Expired
            } catch (JwtException e) {
                // Invalid
            }
        }

        return null;
    }

    @Override
    public boolean isValid(String token) {
        return this.getClaims(token) != null;
    }

    @Override
    public int getId(String token) {
        Claims claims = this.getClaims(token);

        if (claims != null) {
            return Integer.parseInt(claims.get("id").toString());
        }

        return 0;
    }

    @Override
    public String getEmail(String token) {
        Claims claims = this.getClaims(token);
        String userEmail = claims.get("email").toString();

        if (claims != null) {
            return userEmail;
        }

        return null;
    }
}
