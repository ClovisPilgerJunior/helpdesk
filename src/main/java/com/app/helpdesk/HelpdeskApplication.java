package com.app.helpdesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HelpdeskApplication {

  public static void main(String[] args) {
    SpringApplication.run(HelpdeskApplication.class, args);

  }

  @GetMapping("/")
  public String index(){
    return "Olá Mundo!";
  }
}



