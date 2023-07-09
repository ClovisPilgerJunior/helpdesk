package com.app.helpdesk.config;

import com.app.helpdesk.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

  @Autowired
  private DBService dbService;

  @Bean
  public void instanceDB() {
    this.dbService.startDB();
  }
}
