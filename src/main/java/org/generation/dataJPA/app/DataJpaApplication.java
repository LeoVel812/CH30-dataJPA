package org.generation.dataJPA.app;

import java.util.Optional;

import org.generation.dataJPA.app.entity.Customer;
import org.generation.dataJPA.app.repository.CustomerRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DataJpaApplication {

	private static final Logger log = LoggerFactory.getLogger(DataJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DataJpaApplication.class, args);
	}

	/*
	 * @Bean is used to indicate a Spring class, it returns an object that will be
	 * managed by the container Spring
	 * 
	 * When @Bean is used in a method, Spring calls that method and registers the
	 * returned object in the app context as a bean. This bean will be available to
	 * be injected in dependencies in other parts of the app.
	 * 
	 * Command Line Runner is a functional interface given by Spring in which is
	 * defined a run() method that runs once the app spring context is initiated.
	 * This method is commonly used to run tasks for initiating or setup at the
	 * start of the application.
	 * 
	 */

	//@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a few customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findById(1L);
//			Optional<Customer> customer = repository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
			// for (Customer bauer : repository.findByLastName("Bauer")) {
			// log.info(bauer.toString());
			// }
			log.info("");
		};
	}
}
