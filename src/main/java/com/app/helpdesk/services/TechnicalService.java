package com.app.helpdesk.services;

import com.app.helpdesk.domain.Technical;
import com.app.helpdesk.repositories.TechnicalRepository;
import com.app.helpdesk.services.execptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnicalService {

  @Autowired
  private TechnicalRepository technicalRepository;

  public Technical findById(Integer id) {
    Optional<Technical> object = technicalRepository.findById(id);
    return object.orElseThrow(() -> new ObjectNotFoundException("Technical not found with id: " + id));
  }

  public List<Technical> findAll() {
    return technicalRepository.findAll();
  }
}
