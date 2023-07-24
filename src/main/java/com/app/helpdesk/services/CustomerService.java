package com.app.helpdesk.services;

import com.app.helpdesk.domain.Customer;
import com.app.helpdesk.domain.dtos.CustomerDTO;
import com.app.helpdesk.repositories.PersonRepository;
import com.app.helpdesk.repositories.CustomerRepository;
import com.app.helpdesk.services.execptions.ConstraintViolationException;
import com.app.helpdesk.services.execptions.DataIntegrityViolationException;
import com.app.helpdesk.services.execptions.ObjectNotFoundException;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private BCryptPasswordEncoder encoder;

  public Customer findById(Integer id) {
    Optional<Customer> object = customerRepository.findById(id);
    return object.orElseThrow(() -> new ObjectNotFoundException("Customer not found with id: " + id));
  }

  public List<Customer> findAll() {
    return customerRepository.findAll();
  }

  public Customer create(CustomerDTO objectDTO) {
    objectDTO.setId(null);
    objectDTO.setPassword(encoder.encode(objectDTO.getPassword()));
    validateCpfAndEmail(objectDTO);
    Customer newObj = new Customer(objectDTO);
    return customerRepository.save(newObj);
  }

  public Customer update(Integer id, @Valid CustomerDTO objDTO){
    Customer oldObj = findById(id);

    // Realizar a validação apenas se o CPF for alterado
    if (!oldObj.getCpf().equals(objDTO.getCpf())) {
      boolean cpfExists = personRepository.existsByCpf(objDTO.getCpf());
      if (cpfExists) {
        throw new DataIntegrityViolationException("CPF already registered on system!");
      }
    }

    // Realizar a validação apenas se o e-mail for alterado
    if (!oldObj.getEmail().equals(objDTO.getEmail())) {
      boolean emailExists = personRepository.existsByEmail(objDTO.getEmail());
      if (emailExists) {
        throw new DataIntegrityViolationException("E-mail already registered on system!");
      }
    }

    // Atualizar os campos necessários do objeto antigo
    oldObj.setName(objDTO.getName());
    oldObj.setCpf(objDTO.getCpf());
    oldObj.setEmail(objDTO.getEmail());
    oldObj.setPassword(objDTO.getPassword());
    // Atualize outros campos conforme necessário

    return customerRepository.save(oldObj);
  }


  private void validateCpfAndEmail(CustomerDTO objectDTO) {
     boolean cpf = personRepository.existsByCpf(objectDTO.getCpf());
     if(cpf){
       throw new DataIntegrityViolationException("CPF already register on system!");
     }
     boolean email = personRepository.existsByEmail(objectDTO.getEmail());
     if(email) {
      throw new DataIntegrityViolationException("E-Mail already register on system!");
    }
  }

  public void delete(Integer id) {
    Customer obj = findById(id);
    if(obj.getTickets().size() > 0) {
      throw new DataIntegrityViolationException("Customer has a service order and cannot be deleted!");
    }
    customerRepository.deleteById(id);
  }
}
