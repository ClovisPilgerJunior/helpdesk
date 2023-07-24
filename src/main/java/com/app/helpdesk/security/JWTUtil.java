package com.app.helpdesk.security;

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
  private String secretPhrse;

  public String generateToken(String email) {
    return Jwts
        .builder()
        .setSubject(email)
        .setExpiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(SignatureAlgorithm.HS256, secretPhrse.getBytes())
        .compact();
  }
}
