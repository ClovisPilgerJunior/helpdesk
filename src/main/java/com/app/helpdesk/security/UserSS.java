package com.app.helpdesk.security;

import com.app.helpdesk.domain.enums.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserSS implements UserDetails {

  private final Integer id;
  private final String email;
  private final String password;

  private final Collection<? extends GrantedAuthority> Authorities;

  public UserSS(Integer id, String email, String password, Set<Profile> profileSet) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.Authorities = profileSet.stream().map(x -> new SimpleGrantedAuthority(x.getDesc())).collect(Collectors.toSet());
  }

  public Integer getId() {
    return id;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
