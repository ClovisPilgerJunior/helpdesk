package com.app.helpdesk;

import ch.qos.logback.core.net.server.Client;
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
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

	@Autowired
	private TechnicalRepository technicalRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private TicketRepository ticketRepository;

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Technical tec1 = new Technical( "Junior Pilger", "11122233344", "a@a.com.br", "123");
		tec1.addProfiles(Profile.ADMIN);

		Customer cus1 = new Customer("Linus Torvalds", "44433322211", "linux@linux.com.br", "123");

		Ticket t1 = new Ticket(Priority.MEDIUM, Status.GOING, "Ticket 01", "Primeiro chamado", tec1, cus1);

		technicalRepository.saveAll(List.of(tec1));
		customerRepository.saveAll(List.of(cus1));
		ticketRepository.saveAll(List.of(t1));
	}
}
