package com.app.helpdesk.resources;

import com.app.helpdesk.domain.dtos.CredentialDTO;
import com.app.helpdesk.domain.dtos.LoginResponseDTO;
import com.app.helpdesk.security.TokenService;
import com.app.helpdesk.security.UserSS;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationResource {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody @Valid CredentialDTO credentialDTO) {

    var usernamePassword = new UsernamePasswordAuthenticationToken(credentialDTO.email(), credentialDTO.password());
    var auth = this.authenticationManager.authenticate(usernamePassword);

    var token = tokenService.generateToken((UserSS) auth.getPrincipal());
    return ResponseEntity.ok(new LoginResponseDTO(token));
  }
}
