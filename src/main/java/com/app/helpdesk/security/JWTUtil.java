package com.app.helpdesk.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

  @Value("${jwt.expiration}")
  private Long expiration;

  @Value("${jwt.secret}")
  private String secretPhrase;

  public String generateToken(String email) {
    String token = Jwts
        .builder()
        .setSubject(email)
        .setExpiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(SignatureAlgorithm.HS256, secretPhrase.getBytes())
        .compact();
    return token;
  }

  public boolean tokenValido(String token) {
    Claims claims = getClaims(token);
    if (claims != null) {
      String username = claims.getSubject();
      Date expiDate = claims.getExpiration();
      Date now = new Date(System.currentTimeMillis());

      return username != null && expiDate != null && now.before(expiDate);
    }
    return false;
  }

  private Claims getClaims(String token) {
    try {
      return Jwts.parser().setSigningKey(secretPhrase.getBytes()).parseClaimsJws(token).getBody();
    } catch (Exception e) {
      return null;
    }
  }

  public String getUsername(String token) {
    Claims claims = getClaims(token);
    if (claims != null) {
      return claims.getSubject();
    } else {
      return null;
    }
  }
}
