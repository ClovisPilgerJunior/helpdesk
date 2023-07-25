package com.app.helpdesk.config;


import com.app.helpdesk.security.JWTAuthenticationFilter;
import com.app.helpdesk.security.JWTUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private static final String[] PUBLIC_MATCHERS = { "" };

  @Autowired
  private Environment environment;

  @Autowired
  private JWTUtil jwtUtil;

  @Autowired
  private UserDetailsService userDetailsService;

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
    return authConfiguration.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationConfiguration authConfiguration) throws Exception {
    if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
      http.headers().frameOptions().disable();
    }

    http.cors();

    //http.authorizeHttpRequests().requestMatchers(toH2Console()).permitAll();
//    http.addFilter(new JWTAuthenticationFilter(authConfiguration.getAuthenticationManager(), jwtUtil));
    return http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authorizeHttpRequests().requestMatchers("/login").permitAll().anyRequest().authenticated().and()
        .build();


  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
    configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}


