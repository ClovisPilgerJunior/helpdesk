package com.app.helpdesk.services;

import com.app.helpdesk.domain.Person;
import com.app.helpdesk.domain.Technical;
import com.app.helpdesk.domain.dtos.TechnicalDTO;
import com.app.helpdesk.repositories.PersonRepository;
import com.app.helpdesk.repositories.TechnicalRepository;
import com.app.helpdesk.services.execptions.DataIntegrityViolationException;
import com.app.helpdesk.services.execptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnicalService {

  @Autowired
  private TechnicalRepository technicalRepository;

  @Autowired
  private PersonRepository personRepository;

  public Technical findById(Integer id) {
    Optional<Technical> object = technicalRepository.findById(id);
    return object.orElseThrow(() -> new ObjectNotFoundException("Technical not found with id: " + id));
  }

  public List<Technical> findAll() {
    return technicalRepository.findAll();
  }

  public Technical create(TechnicalDTO objectDTO) {
    objectDTO.setId(null);
    validateCpfAndEmail(objectDTO);
    Technical newObj = new Technical(objectDTO);
    return technicalRepository.save(newObj);
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
}
