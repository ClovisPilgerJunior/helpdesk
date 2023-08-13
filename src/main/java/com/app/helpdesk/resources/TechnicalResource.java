package com.app.helpdesk.resources;

import com.app.helpdesk.domain.Technical;
import com.app.helpdesk.domain.dtos.TechnicalDTO;
import com.app.helpdesk.services.TechnicalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = "http://localhost:4200")
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

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  public ResponseEntity<TechnicalDTO> create(@Valid @RequestBody TechnicalDTO objectDTO){
    Technical newObject = technicalService.create(objectDTO);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObject.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }
  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping(value = "/{id}")
  public ResponseEntity<TechnicalDTO> update(@PathVariable Integer id, @Valid @RequestBody TechnicalDTO objDTO) {
    Technical obj = technicalService.update(id, objDTO);
    return ResponseEntity.ok().body(new TechnicalDTO(obj));
  }
  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<String> delete(@PathVariable(value = "id") Integer id) {
    technicalService.delete(id);
    return ResponseEntity.status(HttpStatus.OK).body("Technical with ID " + id + " deleted successfully.");
  }
}
