package com.app.helpdesk.services;

import com.app.helpdesk.domain.Person;
import com.app.helpdesk.domain.Technical;
import com.app.helpdesk.domain.dtos.TechnicalDTO;
import com.app.helpdesk.repositories.PersonRepository;
import com.app.helpdesk.repositories.TechnicalRepository;
import com.app.helpdesk.services.execptions.DataIntegrityViolationException;
import com.app.helpdesk.services.execptions.ObjectNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnicalService {

  @Autowired
  private TechnicalRepository technicalRepository;

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private BCryptPasswordEncoder encoder;

  public Technical findById(Integer id) {
    Optional<Technical> object = technicalRepository.findById(id);
    return object.orElseThrow(() -> new ObjectNotFoundException("Technical not found with id: " + id));
  }

  public List<Technical> findAll() {
    return technicalRepository.findAll();
  }

  public Technical create(TechnicalDTO objectDTO) {
    objectDTO.setId(null);
    objectDTO.setPassword(encoder.encode(objectDTO.getPassword()));
    validateCpfAndEmail(objectDTO);
    Technical newObj = new Technical(objectDTO);
    return technicalRepository.save(newObj);
  }

  public Technical update(Integer id, @Valid TechnicalDTO objDTO){
    Technical oldObj = findById(id);

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

    return technicalRepository.save(oldObj);
  }


  private void validateCpfAndEmail(TechnicalDTO objectDTO) {
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
    Technical obj = findById(id);
    if(obj.getTickets().size() > 0) {
      throw new DataIntegrityViolationException("Technician has a service order and cannot be deleted!");
    }
    technicalRepository.deleteById(id);
  }
}
