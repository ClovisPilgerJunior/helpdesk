package com.app.helpdesk.resources;

import com.app.helpdesk.domain.Technical;
import com.app.helpdesk.domain.dtos.TechnicalDTO;
import com.app.helpdesk.services.TechnicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

  @GetMapping
  public  ResponseEntity<List<TechnicalDTO>> findAll(){
    List<Technical> list = technicalService.findAll();
    List<TechnicalDTO> listDTO = list.stream().map(TechnicalDTO::new).collect(Collectors.toList());
    return ResponseEntity.ok().body(listDTO);
  }

  @PostMapping
  public ResponseEntity<TechnicalDTO> create(@RequestBody TechnicalDTO objectDTO){
    Technical newObject = technicalService.create(objectDTO);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObject.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }
}
