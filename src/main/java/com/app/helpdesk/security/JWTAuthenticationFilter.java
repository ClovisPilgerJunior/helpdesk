package com.app.helpdesk.security;

import com.app.helpdesk.domain.dtos.CredentialDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private AuthenticationManager authenticationManager;
  private JWTUtil jwtUtil;


  public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationManager authenticationManager1, JWTUtil jwtUtil) {
    super(authenticationManager);
    this.authenticationManager = authenticationManager1;
    this.jwtUtil = jwtUtil;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    try {
      CredentialDTO credentialDTO = new ObjectMapper().readValue(request.getInputStream(), CredentialDTO.class);
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(credentialDTO.getEmail(), credentialDTO.getPassword(), new ArrayList<>());
      Authentication authentication = authenticationManager.authenticate(authenticationToken);
      return authentication;
    }catch (Exception e){
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    String username = ((UserSS) authResult.getPrincipal()).getUsername();
    String token = jwtUtil.generateToken(username);
    response.setHeader("access-control-expose-headers", "Authorization");
    response.setHeader("Authorization", "Bearer " + token);
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

    response.setStatus(401);
    response.setContentType("application/json");
    response.getWriter().append(json());
  }

  private CharSequence json() {
    long date = new Date().getTime();
    return "{"
        + "\"timestamp\": " + date + ", "
        + "\"status\": 401, "
        + "\"error\": \"Não autorizado\", "
        + "\"message\": \"Email ou senha inválidos\", "
        + "\"path\": \"/login\"}";
  }
}
