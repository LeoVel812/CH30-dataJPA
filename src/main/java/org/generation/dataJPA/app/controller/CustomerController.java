package org.generation.dataJPA.app.controller;

import java.util.List;
import java.util.Optional;

import org.generation.dataJPA.app.entity.Customer;
import org.generation.dataJPA.app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import lombok.extern.log4j.Log4j2;
@CrossOrigin(origins = "*")
@RestController // @controller and @ResponseBody serializes
@RequestMapping("/api/v1/customers") // localhost:8081/api/v1/customers
@Log4j2 // to print to console
public class CustomerController {
	@Autowired
	CustomerRepository customerRepository;

	@GetMapping
	public List<Customer> getAllCustomers() {
		log.info("GET request for all customers");
		List<Customer> customers = (List<Customer>) customerRepository.findAll();
		return customers;
	}
//	public List<Customer> getAllCustomers() {
//		return (List<Customer>) customerRepository.findAll();
//	}

	@GetMapping("/{id}") // localhost:8081/api/v1/customers/id
	public Customer getCustomerById(@PathVariable("id") long id) {
		Customer customer = customerRepository.findById(id);
//		customer.setLastName("Luna");
		return customer;
//		return customerRepository.findById(id);
	}

	@PostMapping // localhost:8081/api/v1/customers
	public Customer setCustomer(@RequestBody Customer customer) {
		log.info("POST request Saving a new customer");
		// post request is only to create a new customer, not to update
		customer.setId(null);// to ensure that a new customer is created
		Customer newCustomer = customerRepository.save(customer);// creating a new user
		return newCustomer;// return to the request the user created, in order to see the object
	}

	@PutMapping("{id}") // localhost:8081/api/v1/customers/id
	public Customer updateCustomer(@RequestBody Customer customer, @PathVariable long id) {
//		Customer existingCustomer = customerRepository.findById((long) customer.getId());
		Customer existingCustomer = customerRepository.findById(id);
		if (existingCustomer == null) {
			throw new IllegalStateException("User does not exist.");
		}
		existingCustomer.setFirstName(customer.getFirstName());
		existingCustomer.setLastName(customer.getLastName());
		log.info("PUT request updating an existing customer");
		return customerRepository.save(existingCustomer);
	}

	@DeleteMapping("{id}")
	public String deleteCustomer(@PathVariable Long id) {
		/*
		 * long way // customerRepository is overridden and returns an optional, where
		 * inside it, there is a Customer object Optional<Customer> optionalCustomer =
		 * customerRepository.findById(id);// returns optional object
		 * 
		 * if (optionalCustomer.isPresent()) {// if there is a customer object in
		 * Wrapper Optional Customer existingCustomer = optionalCustomer.get(); // Give
		 * me the object inside customerRepository.delete(existingCustomer); // delete
		 * that object return "Customer deleted"; } throw new
		 * IllegalStateException("User does not exist.");
		 */

		// short way:

		customerRepository.delete(
				customerRepository.findById(id).orElseThrow(() -> new IllegalStateException("User does not exist.")));
		return "Customer deleted";
	}
}
