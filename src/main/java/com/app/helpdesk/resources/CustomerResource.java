package com.app.helpdesk.resources;

import com.app.helpdesk.domain.Customer;
import com.app.helpdesk.domain.dtos.CustomerDTO;
import com.app.helpdesk.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/customer")
public class CustomerResource {

  // localhost:8080/technical
  @Autowired
  private CustomerService customerService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<CustomerDTO> findById(@PathVariable Integer id) {
    Customer object = customerService.findById(id);
    return ResponseEntity.ok().body(new CustomerDTO(object));
  }

  @GetMapping
  public  ResponseEntity<List<CustomerDTO>> findAll(){
    List<Customer> list = customerService.findAll();
    List<CustomerDTO> listDTO = list.stream().map(CustomerDTO::new).collect(Collectors.toList());
    return ResponseEntity.ok().body(listDTO);
  }

  @PostMapping
  public ResponseEntity<CustomerDTO> create(@Valid @RequestBody CustomerDTO objectDTO){
    Customer newObject = customerService.create(objectDTO);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObject.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<CustomerDTO> update(@PathVariable Integer id, @Valid @RequestBody CustomerDTO objDTO) {
    Customer obj = customerService.update(id, objDTO);
    return ResponseEntity.ok().body(new CustomerDTO(obj));
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<String> delete(@PathVariable(value = "id") Integer id) {
    customerService.delete(id);
    return ResponseEntity.status(HttpStatus.OK).body("Customer with ID " + id + " deleted successfully.");
  }
}
