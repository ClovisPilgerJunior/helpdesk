package com.app.helpdesk.services;

import com.app.helpdesk.domain.Technical;
import com.app.helpdesk.repositories.TechnicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TechnicalService {

  @Autowired
  private TechnicalRepository technicalRepository;

  public Technical findById(Integer id) {
    Optional<Technical> object = technicalRepository.findById(id);
    return object.orElse(null);
  }
}
