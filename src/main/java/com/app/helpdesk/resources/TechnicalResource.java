package com.app.helpdesk.resources;

import com.app.helpdesk.domain.Technical;
import com.app.helpdesk.domain.dtos.TechnicalDTO;
import com.app.helpdesk.services.TechnicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/technical")
public class TechnicalResource {

  // localhost:8080/technical
  @Autowired
  private TechnicalService technicalService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<TechnicalDTO> findById(@PathVariable Integer id) {
    Technical object = technicalService.findById(id);
    return ResponseEntity.ok().body(new TechnicalDTO(object));
  }

}
