package org.generation.dataJPA.app.controller;

import java.util.List;

import org.generation.dataJPA.app.entity.OrderProduct;
import org.generation.dataJPA.app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.log4j.Log4j2;
@CrossOrigin(origins = "*")
@RestController // @controller and @ResponseBody serializes
@RequestMapping("/api/v1/orders") // localhost:8081/api/v1/orders
@Log4j2 // to print to console
public class OrderController {
	@Autowired
	OrderService orderService;

	@PostMapping
	public ResponseEntity<OrderProduct> createOrder(@RequestBody OrderProduct order) {
		OrderProduct newOrder = orderService.createOrder(order);
		return new ResponseEntity<>(newOrder, HttpStatus.CREATED);// Status 201
	}

	@GetMapping("{id}")
	public ResponseEntity<OrderProduct> getOrderById(@PathVariable Long id) {
		OrderProduct order = orderService.getOrderById(id);
		return new ResponseEntity<>(order, HttpStatus.OK);// Status 200
	}

	@GetMapping
	public ResponseEntity<List<OrderProduct>> getAllOrders() {
		List<OrderProduct> orders = orderService.getAllOrders();
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
	
}
