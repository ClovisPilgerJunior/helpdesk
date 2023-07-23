package com.app.helpdesk.services;

import com.app.helpdesk.domain.Customer;
import com.app.helpdesk.domain.Technical;
import com.app.helpdesk.domain.Ticket;
import com.app.helpdesk.domain.enums.Priority;
import com.app.helpdesk.domain.enums.Profile;
import com.app.helpdesk.domain.enums.Status;
import com.app.helpdesk.repositories.CustomerRepository;
import com.app.helpdesk.repositories.TechnicalRepository;
import com.app.helpdesk.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBService {
  @Autowired
  private TechnicalRepository technicalRepository;
  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private TicketRepository ticketRepository;

  public void startDB(){
    // Criar técnico com perfil administrador
    Technical tec1 = new Technical(null, "Junior Pilger", "61394022042", "a@a.com.br", "123");
    tec1.addProfiles(Profile.ADMIN);

    // Criar técnicos
    Technical tec2 = new Technical(null,"John Doe", "03783764386", "john.doe@example.com", "456");
    Technical tec3 = new Technical(null,"Mary Johnson", "11228847614", "mary.johnson@example.com", "789");
    Technical tec4 = new Technical(null,"Robert Smith", "85752968097", "robert.smith@example.com", "012");
    Technical tec5 = new Technical(null,"Jane Doe", "84445767867", "jane.doe@example.com", "345");
    Technical tec6 = new Technical(null,"Michael Johnson", "62218888327", "michael.johnson@example.com", "678");
    Technical tec7 = new Technical(null,"Emily Smith", "92241975583", "emily.smith@example.com", "901");

    tec2.addProfiles(Profile.TECHNICIAN);
    tec3.addProfiles(Profile.TECHNICIAN);
    tec4.addProfiles(Profile.TECHNICIAN);
    tec5.addProfiles(Profile.TECHNICIAN);
    tec6.addProfiles(Profile.TECHNICIAN);
    tec7.addProfiles(Profile.TECHNICIAN);

    technicalRepository.saveAll(List.of(tec1, tec2, tec3, tec4, tec5, tec6, tec7));

    // Criar clientes
    Customer cus1 = new Customer(null,"Linus Torvalds", "14178441590", "linux@linux.com.br", "123");
    Customer cus2 = new Customer(null,"Alice Johnson", "36562882168", "alice.johnson@example.com", "234");
    Customer cus3 = new Customer(null,"David Smith", "38322565399", "david.smith@example.com", "567");
    Customer cus4 = new Customer(null,"Sarah Doe", "61317166337", "sarah.doe@example.com", "890");
    Customer cus5 = new Customer(null,"Robert Johnson", "21466730790", "robert.johnson@example.com", "123");
    Customer cus6 = new Customer(null,"Emily Davis", "82987592736", "emily.davis@example.com", "456");
   // Customer cus7 = new Customer("Daniel Smith", "99988877766", "daniel.smith@example.com", "789");

    customerRepository.saveAll(List.of(cus1, cus2, cus3, cus4, cus5, cus6));

// Criar tickets
    Ticket t1 = new Ticket(Priority.MEDIUM, Status.GOING, "Ticket 01", "Primeiro chamado", tec1, cus1);
    Ticket t2 = new Ticket(Priority.LOW, Status.OPENED, "Ticket 02", "Segundo chamado", tec2, cus2);
    Ticket t3 = new Ticket(Priority.HIGH, Status.OPENED, "Ticket 03", "Terceiro chamado", tec3, cus3);
    Ticket t4 = new Ticket(Priority.MEDIUM, Status.GOING, "Ticket 04", "Quarto chamado", tec4, cus4);
    Ticket t5 = new Ticket(Priority.LOW, Status.OPENED, "Ticket 05", "Quinto chamado", tec5, cus5);
    Ticket t6 = new Ticket(Priority.HIGH, Status.GOING, "Ticket 06", "Sexto chamado", tec6, cus6);
    Ticket t7 = new Ticket(Priority.MEDIUM, Status.OPENED, "Ticket 07", "Sétimo chamado", tec7, cus6);


    ticketRepository.saveAll(List.of(t1, t2, t3, t4, t5, t6, t7));


  }
}
