package com.qlyshopphone_backend.service.jwt;

import com.qlyshopphone_backend.model.UserSecurityDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {
    private final String privateKey = "ducnhung";

    public String generateToken(Authentication authentication) {
        Map<String, Object> map = new HashMap<>();
        map.put("roles", authentication.getAuthorities());
        UserSecurityDetails userSecurityDetails = (UserSecurityDetails) authentication.getPrincipal();
        String token = Jwts.builder()
                .setId("ducnhung")
                .setExpiration(new Date(new Date().getTime() + 1000000))
                .setSubject(userSecurityDetails.getUsername())
                .addClaims(map)
                .signWith(SignatureAlgorithm.HS512, privateKey)
                .compact();
        return token;
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(privateKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(privateKey).parseClaimsJws(token).getBody().getSubject();
    }
}
