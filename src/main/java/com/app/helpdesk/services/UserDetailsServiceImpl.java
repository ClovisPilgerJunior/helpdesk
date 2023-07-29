package com.app.helpdesk.services;

import com.app.helpdesk.domain.Person;
import com.app.helpdesk.repositories.PersonRepository;
import com.app.helpdesk.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private final PersonRepository personRepository;

  public UserDetailsServiceImpl(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<Person> user = personRepository.findByEmail(email);
    if(user.isPresent()){
      return new UserSS(user.get().getId(), user.get().getEmail(), user.get().getPassword(), user.get().getProfiles());
    }
    throw new UsernameNotFoundException("User Not Found with email: " + email);
  }
}
